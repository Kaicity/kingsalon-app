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
    A[Client - Next.js] --> B[API Gateway]

    B --> C[User Service]
    B --> D[Booking Service]
    B --> E[Salon Service]
    B --> F[Category Service]
    B --> G[Service Offering]
    B --> G[Payment Service]

    C --> DB1[(MySQL)]
    D --> DB2[(MySQL)]
    E --> DB3[(MySQL)]
    F --> DB4[(MySQL)]
    G --> DB5[(MySQL)]
    G --> DB6[(MySQL)]