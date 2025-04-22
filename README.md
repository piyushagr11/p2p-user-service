# 👤 User Service - P2P Chat App

This is the user profile and contacts microservice for the P2P Chat application. It manages user details, authenticated access to profile info, and contact management.

---

## 🚀 Features

- Retrieve logged-in user's profile (`/users/me`)
- Add and view contacts
- JWT-secured endpoints (uses token from `auth-service`)
- Built using Spring WebFlux and Reactive MongoDB
- Seamlessly integrates with `auth-service` and `chat-service`

---

## 🧰 Tech Stack

- Spring Boot 3
- Spring WebFlux
- Spring Security (JWT-based)
- Reactive MongoDB
- Lombok

---

## 📦 API Endpoints

| Method | Endpoint        | Description                      | Auth Required |
|--------|------------------|----------------------------------|---------------|
| GET    | `/users/me`      | Get current logged-in user info | ✅ Yes         |
| GET    | `/contacts`      | Get list of contacts             | ✅ Yes         |
| POST   | `/contacts`      | Add a new contact                | ✅ Yes         |

---

## 🔐 Authorization

All endpoints require a valid JWT token (issued by `auth-service`).

Include it in every request:
```makefile
Authorization: Bearer <token>
```
---
## 📝 Example: Get My Profile

```http
GET /users/me
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```
Response
```json
{
  "id": "66255d79ab843f28b6c79531",
  "username": "userName1",
  "contacts": ["contactName1", "contactName2"]
}
```
---
## 📝 Example: Add Contact
```http
POST /contacts
Authorization: Bearer <token>
Content-Type: application/json

"rohit"
```
## 📝 Running Locally
- Ensure MongoDB is running locally or via Docker.

- Make sure auth-service is running to provide tokens.

- Run the service:
```bash
./mvnw spring-boot:run
```

## 🔄 Dependencies
This service depends on:

- **auth-service** for JWT issuance

- **chat-service** for real-time communication (uses usernames managed here)