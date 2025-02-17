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

## Docker Compose for Elasticsearch, MongoDB, Kafka

```docker
version: '3.8'

services:
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.5.3
    container_name: elasticsearch
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
    ports:
      - "9200:9200"
      - "9300:9300"
    volumes:
      - elasticsearch_data:/usr/share/elasticsearch/data
    networks:
      - cqrs

  kibana:
    image: docker.elastic.co/kibana/kibana:8.5.3
    container_name: kibana
    depends_on:
      - elasticsearch
    environment:
      - ELASTICSEARCH_HOSTS=http://elasticsearch:9200
    ports:
      - "5601:5601"
    networks:
      - cqrs

  mongodb:
    image: mongo:6.0
    container_name: mongodb
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: rootpassword
    ports:
      - "27017:27017"
    volumes:
      - mongodb_data:/data/db
    networks:
      - cqrs

  zookeeper:
    image: confluentinc/cp-zookeeper:7.4.0
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
    networks:
      - cqrs

  kafka:
    image: confluentinc/cp-kafka:7.4.0
    container_name: kafka
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    ports:
      - "9092:9092"
    networks:
      - cqrs

networks:
  cqrs:
    driver: bridge

volumes:
  mongodb_data:
    driver: local
  elasticsearch_data:
    driver: local

```
