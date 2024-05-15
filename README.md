# Product Management System

## 📜 Description

This back-end project is designed to provide robust and scalable solutions for managing product inventories. It includes functionalities for adding, updating, and categorizing products, as well as tracking stock levels. Ideal for educational purposes, this system demonstrates key concepts in back-end development and product management.

## 🛠️ Technologies Used

- **Spring Boot 3**
- **Java 21**
- **Docker**
- **Flyway**
- **Gradle**

## ⚙️ Prerequisites

- Ensure that Docker Engine is installed and running on your machine.

## 📦 Setup and Installation

### Step 1: Clone the Repository
```
git clone https://github.com/yourusername/product-management-system.git
cd product-management-system
```

### Step 2: Run the Application

When you run the project, Docker will automatically start, and Flyway will handle the database migrations.
```
./gradlew bootRun
```

### Step 3: Access the Application

The application will start on port `8080` and will be accessible at the base path `/pd-mng`:

```
http://localhost:8080/pd-mng
```

## 🐳 Docker Setup

A `docker-compose.yml` file is included and will be executed when the project is run. This file will set up all necessary dependencies:

- **MySQL**
- **MongoDB**

Ensure that Docker is running to avoid any issues.

### Running Docker Compose Manually

If you need to run Docker Compose manually, use the following command:

if you have make installed:
```
make run-deps
```
 if you don't have make installed:
```
cd product-manager-docker && docker-compose -f compose.yaml up
```


## 🚀 Database Migrations

Flyway is used for managing database migrations and will automatically run to initialize the database after Docker starts.

## 📚 Dependencies and Libraries

- **JPA**
- **Spring Data MongoDB**
- **Spring Validation**
- **Spring DevTools**
- **Lombok**
- **Configuration Processor**
- **Swagger**

## 📖 API Documentation

Swagger is integrated for API documentation. Once the application is running, you can access the API documentation at:

```
http://localhost:8080/pd-mng/swagger-ui.html
```

## 📝 Lombok Configuration

Lombok is used to reduce boilerplate code. Ensure that you have the Lombok plugin installed and enabled in your IDE.

## 🛠️ Development Tools

Spring DevTools is included to provide an enhanced development experience with features like automatic restarts and live reloads.

## 🤝 Contributing

We welcome contributions! Feel free to open issues or submit pull requests with improvements or new features.

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---
