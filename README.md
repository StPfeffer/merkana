# Merkana Backend

**Merkana** is a **modular, scalable, and extensible e-commerce backend** built using a clean architecture approach.
This repository implements the backend services powering the core APIs, business logic, and integrations of the Merkana
platform.

## Overview

Merkana supports modern e-commerce capabilities including:

* Product catalog and inventory management
* Orders, payments, and shipping workflows
* Customer profiles and account management
* Analytics and reporting
* Promotions, discounts, and loyalty programs

The backend is structured as a **multi-module project**, ensuring:

* High maintainability
* Clear separation of concerns
* Easy scalability and future microservice evolution

It is implemented in **Java 21+**, optionally with Kotlin, and uses Maven for modular project management with explicit
domain boundaries.

## Architecture

Merkana follows a **modular monolith** pattern with potential for microservice decomposition. Key characteristics:

* Each module represents a **specific business domain or infrastructural concern**
* Dependencies are **centrally managed** via a dedicated BOM module
* Clean architecture ensures **decoupling of business logic, infrastructure, and API layers**

## Technologies Used

* **Programming Languages:** Java 21+, Kotlin (optional)
* **Framework:** Spring Boot
* **APIs:** GraphQL and REST
* **Databases & Caching:** PostgreSQL, MongoDB, Redis
* **Search & Analytics:** Elasticsearch
* **Build Tool:** Maven (modular multi-project build)
* **Containerization:** Docker

## Development

### Prerequisites

* JDK 21+
* Maven 3.8+
* Docker (for local supporting services)
* Redis, PostgreSQL, MongoDB, Elasticsearch (depending on your environment)

### Environment Configuration

The project uses different configuration files depending on the execution environment:

* **`.env.dev`** – Settings for the development environment
* **`.env.prd`** – Settings for the production environment

#### Creating Environment Files

1. **For Development**:

   ```bash
   cp .env.example .env.dev
   ```

2. **For Production**:

   ```bash
   cp .env.example .env.prd
   ```

#### Required Variables

Based on the [.env.example](.env.example) file, configure the following variables:

```env
# Auth
JWT_SECRET_KEY=your_jwt_secret_key_here

# PostgreSQL Database
PG_NAME=your_postgres_database
PG_URL=jdbc:postgresql://localhost:5432/your_postgres_database
PG_USER=your_postgres_username
PG_PASSWORD=your_postgres_password
PG_PORT=5432
PG_DB=your_postgres_database

# Redis Cache
REDIS_NAME=merkana_redis
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_URL=redis://localhost:6379
REDIS_USER=your_redis_username
REDIS_PASSWORD=your_redis_password
REDIS_DATABASE=0

# Elasticsearch
ELASTIC_HOST=localhost:9300
ELASTIC_USERNAME=your_elastic_username
ELASTIC_PASSWORD=your_elastic_password
```

### Configuration with Docker Compose

The included `docker-compose.yml` file simplifies the startup and management of the PostgreSQL database used by the
system. It also automates the setup of Prometheus and Grafana containers for monitoring and observability purposes.

For more details, see the [docker-compose.yml](docker-compose.yml) file.

### Running the Application

```bash
mvn clean install
cd modules/merkana-web
mvn spring-boot:run
```

### Recommended Local Setup

Using Docker Compose for local development:

```bash
docker-compose up -d
```

This will start:

* PostgreSQL
* Redis
* Elasticsearch
* MongoDB

## Observability

Merkana provides built-in observability features:

* Centralized logging
* Audit trails for critical actions
* Metrics export (Prometheus compatible)
* Usage analytics

## Testing

* Unit tests per module
* Integration tests in the `sandbox` module
* Mock services for external dependencies

Run tests with:

```bash
mvn test
```

## Contributing

Contributions are welcome!

* For **major changes**, please open an issue to discuss before submitting a pull request
* For **bug fixes or small enhancements**, submit a PR directly

> For questions or support, contact the maintainers or open an issue in this repository.

## Docker

[Guide on running with Docker](docs/docker-guide.md)

## Code Analysis with Qodana

**Qodana** is JetBrains’ static code analysis solution that identifies issues, vulnerabilities, and improvement
opportunities in your Java/Spring Boot code, providing:

* **Bug detection** before code reaches production
* **Automated code quality metrics**
* **Continuous integration** with detailed reports
* **Support for 2000+ checks** specific to Java

[Guide on running Qodana locally](docs/qodana-guide.md)

### Key Benefits

* Coverage of **100% of IntelliJ IDEA rules**
* Detection of **OWASP Top 10 vulnerabilities**
* Compatible with **Java 21 and Spring Boot 3.x**
* Native integration with **GitLab CI/CD**
