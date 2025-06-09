
# 📦 Inventory Management System

A robust and secure Inventory Management System built with **Spring Boot**, **Spring Security**, **Thymeleaf**, **MongoDB**, and the **CQRS (Command Query Responsibility Segregation)** pattern. Designed for managing product stock and user access in a scalable and maintainable way.

---

## 🚀 Features

- 🔐 **Authentication & Authorization** – Role-based access using Spring Security
- 👤 **User Management** – Admin and regular user roles
- 📦 **Product Inventory** – Add, update, delete, and list inventory items
- 🖥️ **Thymeleaf UI** – Clean and responsive server-rendered HTML templates
- 🔄 **CQRS Architecture** – Separate read and write logic for better scalability and testability
- 🍃 **MongoDB** – Fast and flexible NoSQL data storage using Spring Data MongoDB

---

## 🛠️ Tech Stack

| Layer              | Technology                |
|-------------------|---------------------------|
| Backend Framework | Spring Boot               |
| Security          | Spring Security           |
| Template Engine   | Thymeleaf                 |
| Database          | MongoDB                   |
| Data Access       | Spring Data MongoDB       |
| Architecture      | CQRS (Command/Query split)|
| Build Tool        | Maven or Gradle           |

---

## 📁 Project Structure

\`\`\`
src/
├── config/                 # Security and MongoDB configurations
├── controller/
│   ├── command/            # Write operations (Create, Update, Delete)
│   └── query/              # Read operations (List, Get)
├── service/
│   ├── command/            # Command-side services
│   └── query/              # Query-side services
├── dto/                    # Data transfer objects
├── model/                  # MongoDB entity models
├── repository/             # Mongo repositories
├── security/               # JWT filters, user details, config
└── templates/              # Thymeleaf HTML views
\`\`\`

---

## ⚙️ Getting Started

### Prerequisites

- Java 17+
- Maven or Gradle
- MongoDB (local or MongoDB Atlas)
- Optional: Docker (for containerized setup)

### Clone the Project

\`\`\`bash
git clone https://github.com/fugugdeveloper/Inventory-management-system-with-spring-boot-Spring-Security-Thymeleaf-template-and-CQRS.git
cd Inventory-management-system-with-spring-boot-Spring-Security-Thymeleaf-template-and-CQRS
\`\`\`

### Setup MongoDB

- Local: Start your MongoDB server on \`localhost:27017\`
- OR
- Use [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) and replace the URI in \`application.properties\`:

\`\`\`properties
spring.data.mongodb.uri=mongodb://localhost:27017/inventorydb
\`\`\`

### Run the Project

Using Maven:

\`\`\`bash
./mvnw spring-boot:run
\`\`\`

Using Gradle:

\`\`\`bash
./gradlew bootRun
\`\`\`

---

## 🔐 Login Credentials

| Role   | Username | Password |
|--------|----------|----------|
| Admin  | admin    | admin123 |
| User   | user     | user123  |

*(These are default in-memory values. You can configure or persist users as needed.)*

---

## 📸 Screenshots

> You can add screenshots of your:
> - Login page
> - Dashboard
> - Product list and forms

---

## 📌 TODOs

- [ ] Add pagination and search filters
- [ ] Integrate RabbitMQ or Kafka for async event handling
- [ ] Add REST API layer (optional)
- [ ] Dockerize the application
- [ ] Add GraphQL support (if needed)

---

## 🧾 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 🙌 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## 📬 Contact

For questions or feedback, contact [fandisheabdurehman@gmail.com](mailto:fandisheabdurehman@gmail.com)
