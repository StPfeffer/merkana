# Execution and Configuration with Docker

## Summary

* [Docker](#docker)
* [Docker Compose](#docker-compose)
* [Logs and Monitoring](#logs-and-monitoring)

## Docker

```bash
# Build the image
docker build -t merkana-api:latest .

# Export image
docker save merkana-api:latest > merkana-api.tar

# Import image
docker load < merkana-api.tar

# Run container
docker-compose up -d merkana-api
```

## Docker Compose

```bash
docker-compose up -d merkana-api
```

## Logs and Monitoring

```bash
# Capture logs
docker logs <container-id> > server.log

# Check resource usage
docker stats <container-id>
```

### **Required Files**

1. **`Dockerfile`** (at the root of the project):

   ```dockerfile
   FROM eclipse-temurin:21-jdk
   COPY target/merkana-api-*.jar merkana.jar
   ENTRYPOINT ["java", "-jar", "merkana.jar"]
   ```

2. **`docker-compose.yml`** (optional):

   ```yaml
   services:
     merkana-api:
       image: merkana-api:latest
       ports:
         - "9090:9090"
       environment:
         SERVER_PORT: 9090
   ```
