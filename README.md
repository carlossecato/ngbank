# 🏦 Banking API Project - Spring Boot + MySQL 8

This project implements a **REST API** for managing bank accounts and financial transactions.  

Technologies used:
- Java 17
- Spring Boot
- MySQL 8 (via Docker Compose)
- Maven
- JUnit 5 / Mockito (tests)
- Insomnia (for API testing)

---

## 🚀 Features

- **Create account** with account number and initial balance.
- **Get account** by account number.
- **Register transactions** (Pix, Debit, Credit) with applicable fees:
  - Pix: 0%
  - Debit: 3%
  - Credit: 5%
- **Validations**:
  - Account must exist to perform transactions.
  - Negative balance is not allowed.

---

## 🐳 Running the environment with Docker Compose

### 1. Requirements
- Docker and Docker Compose installed
- Java 17
- Maven

### 2. Clone the repository
```bash
git clone https://github.com/carlossecato/ngbank.git
cd ngbank

### 3. Build the application JAR
mvn clean package -DskipTests

### 4. Start containers (Mysql + Application)
docker-compose up -d --build
API will be available at: http://localhost:8080
MySQL will be available at: localhost:3306

### 5. Database Configuration
The docker-compose.yml file automatically creates the database with the following credentials:
 - Database: meubanco
 - User: user
 - Password: pass
 - Root password: root123