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
