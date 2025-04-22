# User Service

The **User Service** is a Spring Boot application that provides user authentication and management functionalities for a peer-to-peer chat application. It uses JWT for authentication and MongoDB as the database.

## Features

- User authentication using JWT.
- Secure password storage with BCrypt.
- Role-based access control for API endpoints.
- Reactive programming with Spring WebFlux.
- MongoDB integration for user data storage.

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **Spring WebFlux**: Reactive programming support.
- **Spring Security**: Security framework for authentication and authorization.
- **MongoDB**: NoSQL database for storing user data.
- **JWT**: JSON Web Tokens for stateless authentication.
- **Maven**: Build and dependency management tool.

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- MongoDB installed and running on `localhost:27017`

## Configuration

The application configuration is stored in `src/main/resources/application.properties`. Update the following properties as needed:

```ini
server.port=8082
spring.data.mongodb.uri=mongodb://localhost:27017/p2p-auth
jwt.secret=yoursupersecretkeywhichisverylong1231234567890

```

## API Endpoints

| Endpoint               | Method | Description                     | Authentication |
|------------------------|--------|---------------------------------|----------------|
| `/me`                 | GET    | Get current user details        | Required       |
| `/users/**`           | CRUD   | Manage user resources           | Required       |
| `/contacts/**`        | CRUD   | Manage user contacts            | Required       |
