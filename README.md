# 🚀 Spring Boot Async Processing Demo

This project demonstrates the difference between **sequential execution** and **parallel execution** using `CompletableFuture` in a Spring Boot application.

The main goal is to show how asynchronous processing can significantly **reduce response time** and improve **application scalability**, especially for I/O-bound operations like external API calls.

---

## 🧠 Overview
* 🔴 **Synchronous (Sequential Execution)**
* 🟢 **Asynchronous (Parallel Execution with CompletableFuture)**
---

## ⚙️ Technologies Used
* Java 21+
* Spring Boot
* CompletableFuture
* ThreadPoolTaskExecutor
---

## 🔴 Endpoint: Sequential Execution
**GET** `/sem-paralelismo`

Executes tasks one after another.

### Behavior:

* Each task takes ~3 seconds
* Total execution time ≈ **9 seconds**

![img_1.png](src/main/resources/images/img_1.png)
---

## 🟢 Endpoint: Parallel Execution
**GET** `/com-paralelismo`

Executes tasks concurrently using `CompletableFuture`.

### Behavior:

* Tasks run in parallel
* Total execution time ≈ **3 seconds**

![img_2.png](src/main/resources/images/img_2.png)
---

### 1. Custom Thread Pool

A dedicated `ThreadPoolTaskExecutor` is used to:

* Control concurrency
* Avoid overloading the system
* Improve performance predictability

### 2. Non-blocking Processing
`CompletableFuture` allows asynchronous execution without blocking the main thread.

### 4. Timeout Handling
Each async task includes a timeout to prevent long-running operations from blocking the system.

### 5. Fallback Strategy
Basic error handling is implemented using `exceptionally()` to ensure resilience.

---
## ▶️ How to Run

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```
---

## 🌐 Test Endpoints

* http://localhost:8080/sem-paralelismo
* http://localhost:8080/com-paralelismo

---

## 📌 Final Thoughts

In real-world applications, this approach is commonly used for:

* External API integrations
* Microservices communication
* Data aggregation from multiple sources
---
