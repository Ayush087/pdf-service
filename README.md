# PDF Service

## Overview
PDF Service is a Spring Boot application that provides APIs for scanning and looking up PDF files. The application uses H2 in-memory database for storing scan results and Spring Security for securing the endpoints.

## Features
- Scan PDF files and process them asynchronously.
- Look up scan results by SHA-256 hash.
- Secure endpoints with role-based access control.

## Technologies Used
- Java
- Spring Boot
- Spring Security
- H2 Database
- Maven

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/Ayush087/pdf-service.git
   cd pdf-service
   
2. Build the project using Maven:
   ```sh
    mvn clean install
   
3. Run the application:
4. Run the application:
   ```sh
   mvn spring-boot:run
   
## Usage

### Scan PDF
1. Upload a PDF file to scan:
   - POST /api/scan
   - Request Body:
    ```json
    {
      "file": "base64-encoded-pdf-file"
    }
    ```
2. Get scan result by SHA-256 hash:
3. Get scan result by SHA-256 hash:
   - GET /api/scan/{hash}
   - Response Body:
   ```json
   {
     "hash": "SHA-256 hash",
     "status": "CLEAN | INFECTED | PENDING"
   }
   ```
   
### User Authentication
1. Register a new user:
   - POST /api/auth/register
   - Request Body:
    ```json
    {
      "username": "user",
      "password": "password"
    }
    ```

2. Authenticate user:
3. Authenticate user:
   - POST /api/auth/login
   - Request Body:
   ```json
   {
     "username": "user",
     "password": "password"
   }
   ```
   - Response Body:
   ```json
   {
     "token": "JWT token"
   }
   ```
   
### User Authorization
1. Get all scan results:
   - GET /api/scan
   - Requires ADMIN role.
   - Add JWT token in Authorization header.
   - Response Body:
   ```json
   [
     {
       "hash": "SHA-256 hash",
       "status": "CLEAN | INFECTED | PENDING"
     }
   ]
   ```
   
## API Endpoint

### Scan Endpoints
- Scan a PDF file:
  - POST /api/scan
  - Request Body:
   ```json
   {
     "file": "base64-encoded-pdf-file"
   }
   ```
- Get scan result by SHA-256 hash:
- Get scan result by SHA-256 hash:
  - GET /api/scan/{hash}
  - Response Body:
  ```json
  {
    "hash": "SHA-256 hash",
    "status": "CLEAN | INFECTED | PENDING"
  }
  ```
  
### Authentication Endpoints
- Register a new user:
  - POST /api/auth/register
  - Request Body:
   ```json
   {
     "username": "user",
     "password": "password"
   }
   ```

- Authenticate user:
- Authenticate user:
  - POST /api/auth/login
  - Request Body:
  ```json
  {
    "username": "user",
    "password": "password"
  }

### Authorization Endpoints
- Get all scan results:
  - GET /api/scan
  - Requires ADMIN role.
  - Add JWT token in Authorization header.
  - Response Body:
  ```json
  [
    {
      "hash": "SHA-256 hash",
      "status": "CLEAN | INFECTED | PENDING"
    }
  ]
  ```
  
## License
This project is licensed under the MIT License. See the LICENSE file for details.
```

[]: # (END) README.md
