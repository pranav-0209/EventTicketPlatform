# 🎟️ Event Ticket Platform API

A comprehensive **REST API** for an event ticketing platform, built with **Spring Boot** and secured with **Keycloak**. This platform allows organizers to create and manage events, while attendees can purchase tickets and have them validated via QR codes.

The system is designed with a **role-based access control** model for **Organizers**, **Staff**, and general **Users** (attendees).

## ✨ Core Features

* **Event Management**: Organizers can create, update, list, and delete their events.
* **Ticket Purchasing**: Authenticated users can purchase available tickets for published events.
* **Role-Based Access**: Secure endpoints using Keycloak for different user roles (e.g., `ORGANIZER`, `STAFF`).
* **QR Code Generation**: Automatically generates a unique QR code for every ticket purchased.
* **Ticket Validation**: Staff can validate tickets either by scanning a QR code or manually by ticket ID.
* **Public Event Listings**: Unauthenticated users can browse and view details of all published events.
* **Dockerized Environment**: Includes a `docker-compose.yml` for easy setup of PostgreSQL and Keycloak.

## 💻 Technologies Used

* **Backend**: Spring Boot 3
* **Database**: PostgreSQL
* **Authentication**: Spring Security with OAuth2 & Keycloak
* **Data Persistence**: Spring Data JPA (Hibernate)
* **QR Codes**: ZXing ("Zebra Crossing") library
* **Code Generation**: Lombok & MapStruct
* **Build Tool**: Maven

## 🚀 Getting Started

### Prerequisites

* Java 21 or higher
* Docker and Docker Compose
* Maven

### Running the Application

1.  **Clone the Repository**:
    ```bash
    git clone [https://github.com/pranav-0209/eventticketplatform.git](https://github.com/pranav-0209/eventticketplatform.git)
    cd eventticketplatform
    ```

2.  **Start the Services (Database & Keycloak)**:
    Use Docker Compose to launch the required services in the background.
    ```bash
    docker-compose up -d
    ```
    This will start:
    * A **PostgreSQL** database on port `5432`.
    * An **Adminer** instance for database management on port `8888`.
    * A **Keycloak** server on port `9090`.

3.  **Configure Keycloak**:
    Navigate to `http://localhost:9090` and log in with `admin/admin`.
    * Create a new realm named `event-ticket-platform`.
    * Create a client and the necessary roles (`ROLE_ORGANIZER`, `ROLE_STAFF`) and users.

4.  **Run the Spring Boot Application**:
    You can run the application directly from your IDE or use the Maven wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```
    The API will be available on `http://localhost:8080`.


---
## 📁 Project Structure
└── src/main/java/com/pranav/tickets/
<br>├── config/            Security, JWT, JPA configurations
<br>├── controllers/       REST API endpoints
<br>├── domain/            Request objects and Entities
<br>├── dtos/              Data Transfer Objects
<br>├── exceptions/        Custom exception classes
<br>├── filters/           Servlet filters (e.g., User Provisioning)
<br>├── mappers/           MapStruct object mappers
<br>├── repositories/      Spring Data JPA repositories
<br>├── services/          Business logic interfaces and implementations
<br>└── util/              Utility classes (e.g., JwtUtil)

## 📝 API Endpoints

Here are the primary endpoints available in the API.

### Events (Organizer Role)

* `POST /api/v1/events` - Create a new event.
* `PUT /api/v1/events/{eventId}` - Update an existing event.
* `GET /api/v1/events` - List all events for the authenticated organizer.
* `GET /api/v1/events/{eventId}` - Get details of a specific event.
* `DELETE /api/v1/events/{eventId}` - Delete an event.

### Published Events (Public Access)

* `GET /api/v1/published-events` - Get a paginated list of all published events.
* `GET /api/v1/published-events?q={query}` - Search published events.
* `GET /api/v1/published-events/{eventId}` - Get public details of a specific event.

### Tickets (Authenticated User)

* `POST /api/v1/events/{eventId}/ticket-types/{ticketTypeId}/tickets` - Purchase a ticket.
* `GET /api/v1/tickets` - List all tickets for the authenticated user.
* `GET /api/v1/tickets/{ticketId}` - Get details for a specific ticket.
* `GET /api/v1/tickets/{ticketId}/qr-codes` - Get the QR code image for a ticket.

### Ticket Validation (Staff Role)

* `POST /api/v1/ticket-validations` - Validate a ticket using its QR code ID or manually with the ticket ID.
## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks!
