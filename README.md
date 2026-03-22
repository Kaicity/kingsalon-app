# 💇‍♂️ King Salon App (Microservices)

A full-stack salon booking system built with microservices architecture.  
This project demonstrates scalable backend design and modern frontend integration.

---

## 🚀 Tech Stack

### Frontend

- Next.js
- Tailwind CSS
- Shadcn UI

### Backend (Microservices)

- Java, Spring Boot, Hibernate/JPA, Lombok, MapStruct
- MySQL, Firebase
- JWT Authentication
- WebSocket (Socket.IO)

---

## 🧩 System Architecture

```mermaid
graph TD

    subgraph Client
        A[Next.js App]
    end

    subgraph Gateway
        B[API Gateway]
    end

    subgraph Services
        C[User Service]
        D[Booking Service]
        E[Salon Service]
        F[Category Service]
        G[Service Offering]
        H[Payment Service]
    end

    subgraph Databases
        DB1[(User DB)]
        DB2[(Booking DB)]
        DB3[(Salon DB)]
        DB4[(Category DB)]
        DB5[(Service DB)]
        DB6[(Payment DB)]
    end

    A --> B

    B --> C
    B --> D
    B --> E
    B --> F
    B --> G
    B --> H

    C --> DB1
    D --> DB2
    E --> DB3
    F --> DB4
    G --> DB5
    H --> DB6