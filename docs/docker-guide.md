# Execução e Configuração com Docker

## Sumário

- [Docker](#docker)
- [Docker Compose](#docker-compose)
- [Logs e Monitoramento](#logs-e-monitoramento)

## Docker

```bash
# Build da imagem
docker build -t merkana-api:latest .

# Exportar imagem
docker save merkana-api:latest > merkana-api.tar

# Importar imagem
docker load < merkana-api.tar

# Executar container
docker-compose up -d merkana-api
```

## Docker Compose

```bash
docker-compose up -d merkana-api
```

## Logs e Monitoramento

```bash
# Capturar logs
docker logs <container-id> > server.log

# Ver consumo de recursos
docker stats <container-id>
```

### **Arquivos Necessários**

1. **`Dockerfile`** (na raiz do projeto):
    ```dockerfile
   FROM eclipse-temurin:21-jdk
   COPY target/merkana-api-*.jar app.jar
   ENTRYPOINT ["java", "-jar", "app.jar"]
    ```

2. **`docker-compose.yml`** (opcional):
    ```yaml
    services:
      merkana-api:
        image: merkana-api:latest
        ports:
          - "9090:9090"
        environment:
          SERVER_PORT: 9090
    ```
