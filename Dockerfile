FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /build

COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY modules modules

RUN ./mvnw clean package -DskipTests -Dcheckstyle.skip

FROM eclipse-temurin:21-jre-jammy AS runtime

RUN apt-get update && \
    apt-get install -y --no-install-recommends curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /usr/share/man/* && \
    rm -rf /usr/share/doc/* && \
    find /var/log -type f -exec truncate --size 0 {} \; && \
    mkdir -p /merkana && \
    chmod -R 755 /merkana

RUN mkdir -p /merkana && \
    addgroup --system --gid 1001 merkanauser && \
    adduser --system --uid 1001 --ingroup merkanauser --no-create-home merkanauser && \
    chown -R merkanauser:merkanauser /merkana

WORKDIR /merkana
USER merkanauser

COPY --from=builder --chown=merkanauser:merkanauser /build/modules/merkana-web/target/*.jar merkana.jar

RUN echo '#!/bin/bash\ncurl -f http://localhost:9090/actuator/health || exit 1' > /merkana/healthcheck.sh && \
    chmod +x /merkana/healthcheck.sh

ENV JAVA_OPTS="\
    -XX:+UseContainerSupport \
    -XX:MaxRAMPercentage=75 \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath=/tmp \
    -XX:+UseG1GC \
    -XX:+UseStringDeduplication \
    -Djava.security.egd=file:/dev/./urandom"

EXPOSE 9090

HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD /merkana/healthcheck.sh

ENTRYPOINT exec java $JAVA_OPTS -jar merkana.jar