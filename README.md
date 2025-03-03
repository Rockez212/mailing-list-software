# Mailing List API

## Description
Mailing List API — is a service for authentication and management of users with JWT tokens. Includes registration, login, token refresh and route protection.

## Technology stack
- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Lombok
- BCrypt
- PostgreSQL / MySQL (for storing users)
- Hibernate / JPA

## Installation and launch
### 1. Cloning a repository
```sh
git clone https://github.com/Rockez666/mailing-list-software
cd mailing-list-software
```

### 2. Configuration
create file `application.properties` in `src/main/resources/` 
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
jwt_secret=SECRET228
```

### 3. Assembly and launch
```sh
mvn clean install
mvn spring-boot:run
```

## API Endpoints
### 1. Registration users
**POST /auth/register**
```json
{
  "username": "your_username",
  "email": "your_email",
  "password": "your_password"
}
```
Response:
```json
"Registered"
```

### 2. Login in system
**POST /auth/login**
```json
{
  "username": "your_username",
  "password": "your_password"
}
```
Response:
```json
{
  "accessToken": "JWT_ACCESS_TOKEN",
  "refreshToken": "JWT_REFRESH_TOKEN"
}
```

### 3. Refresh Access Token
**POST /auth/refresh**
```json
{
  "refreshToken": "JWT_REFRESH_TOKEN"
}
```
Response:
```json
{
  "accessToken": "NEW_JWT_ACCESS_TOKEN",
  "refreshToken": "OLD_REFRESH_TOKEN"
}
```

### 4. Request with authorization
**POST /request/register**

**Headline:**
```
Authorization: Bearer JWT_ACCESS_TOKEN
```
Request body:
```json
{
  "email": "your_email",
  "text": "your_text!"
}
```

## Development
- **src/main/java/com/soft/mailinglist** – the main logic of the project
- **service/** – authentication business logic
- **controller/** – REST API conroller
- **security/** – Spring Security settings
- **util/** – JWT-utill

****
