# CISD-6: Car Management System (Security)

Web application built with **Spring Boot**, **Spring MVC**, **Thymeleaf**, **Spring Security**, **JPA**, and **PostgreSQL in Docker**.

In this practical work the app from CISD‑5 was extended with **user accounts**, **registration**, and **role‑based access control**.

---

## Features

- CRUD operations for cars (create, read, update, delete)
- Search cars by brand
- Input validation on forms
- Minimalist UI with Thymeleaf templates
- User registration and login form
- Role-based access:
    - `USER` can view and search cars
    - `ADMIN` can also add, edit, and delete cars
- Initial admin user is created on startup:
    - username: `admin`
    - password: `admin123`

---

## Security Rules (Summary)

- Public:
    - `/`, `/home`, `/cars/home`
    - `/login`, `/register`
- Authenticated users only:
    - `GET /cars`, `GET /cars/search`
- Admin only:
    - `/cars/add`
    - `/cars/edit/{id}`
    - `/cars/delete/{id}`

Login is implemented with form‑based authentication; logout is available via POST `/logout`.

---

## Setup & Run

### 1. Start PostgreSQL in Docker

```
docker run --name cars-postgres
-e POSTGRES_PASSWORD=12345
-e POSTGRES_USER=user
-e POSTGRES_DB=carsdb
-p 5432:5432
-d postgres:latest
```

The application expects:

- URL: `jdbc:postgresql://localhost:5432/carsdb`
- username: `user`
- password: `12345`

(These values are configured in `application.properties`.)

### 2. Run the application

`./mvnw spring-boot:run`

### 3. Open in browser

`http://localhost:8080/`

You can:

- register a new user and login as `USER`, or
- login as the pre-created admin: `admin` / `admin123` to manage cars fully.

---

## Modified from Previous Work

This project is based on **CISD‑5 Car Management System**.  
Compared to the previous practical work, the following was added:

- `AppUser` entity, repository and service for user details
- registration and login flow
- Spring Security configuration with roles
- initial admin user bootstrapping on startup

