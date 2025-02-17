```markdown
# CQRS Microservice Architecture with Spring Boot, MongoDB, Kafka, and Elasticsearch

This project implements the **Command Query Responsibility Segregation (CQRS) pattern** using **Spring Boot**, **MongoDB**, **Kafka**, and **Elasticsearch**. The architecture is designed to handle high-performance reads and writes separately while ensuring data consistency.

## 📌 Technologies Used
- **Java 17** (or latest LTS)
- **Spring Boot** (Spring Data MongoDB, Spring Kafka, Spring Web)
- **MongoDB** (Transactional Database)
- **Apache Kafka** (Event Streaming)
- **Elasticsearch** (Optimized Read Model)
- **Docker & Docker Compose** (For containerized services)

## 🏗️ Architecture
- **Command Service:** Handles all **write operations** and publishes events to Kafka.
- **Query Service:** Listens to Kafka events, processes data, and stores denormalized views in Elasticsearch for optimized read performance.
- **Kafka:** Acts as an event bus for asynchronous processing and eventual consistency.
- **MongoDB:** Stores normalized transactional data.
- **Elasticsearch:** Provides fast search and retrieval capabilities.

## ⚙️ How It Works
1. **Write Flow:**  
   - Clients send commands (e.g., create/update) to the **Command Service**.
   - The service stores the data in **MongoDB** and publishes an event to **Kafka**.
2. **Read Flow:**  
   - The **Query Service** listens for Kafka events.
   - It processes and indexes data into **Elasticsearch**.
   - Clients query the **Query Service**, which fetches data from Elasticsearch for fast retrieval.

