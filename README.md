# Location Tracker Application

A Spring Boot application that tracks user locations and awards points based on geographical boundaries within India.

## Features

- User registration and authentication with JWT
- Location tracking with points system:
  - Different town: 1 point
  - Different district: 3 points
  - Different state: 10 points
  - Different country: 0 points (India only)
- Live ranking system
- Secure password storage with BCrypt
- JWT-based authentication

## Tech Stack

- Java 17
- Spring Boot 3.2.3
- Spring Security
- Spring Data JPA
- H2 Database
- JWT for authentication
- Maven

## Project Structure

```
src/main/java/com/locationtracker/
├── config/                 # Configuration classes
├── controller/            # REST controllers
├── dto/                   # Data Transfer Objects
├── entity/               # JPA entities
├── exception/            # Exception handlers
├── repository/           # JPA repositories
├── security/             # Security related classes
├── service/              # Business logic
└── LocationTrackerApplication.java
```

## Setup Instructions

1. Clone the repository
2. Make sure you have Java 17 installed
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The application will start on port 8080

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user

### Location
- `POST /api/location/update/{userId}` - Update user location
- `GET /api/location/rankings` - Get user rankings
- `GET /api/location/history/{userId}` - Get user location history

## Security

- All endpoints except `/api/auth/**` require JWT authentication
- Passwords are encrypted using BCrypt
- JWT tokens expire after 24 hours

## Database

- H2 in-memory database
- Access H2 console at: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:locationdb`
  - Username: `sa`
  - Password: `password` 