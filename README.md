# CISD-5: Car Management System

Web application built with **Spring MVC**, **Thymeleaf**, **JPA**, and **PostgreSQL in Docker**.

---

## Features

- *CRUD operations*
- *Search by brand*
- *Input validation*
- *Minimalist UI*
- *Dockerized PostgreSQL*

---

## Setup & Run

### 1. Start PostgreSQL in Docker

```bash
docker run --name cars-postgres \
  -e POSTGRES_PASSWORD=12345 \
  -e POSTGRES_USER=user \
  -e POSTGRES_DB=carsdb \
  -p 5432:5432 \
  -d postgres:latest
```

### 2. Run the application

```bash
./mvnw spring-boot:run
```

### 3. Open in browser
→ http://localhost:8080/
