# Product Management REST API

A Spring Boot REST API for managing products, built with a flexible service-layer architecture that supports two interchangeable data sources — an external API (FakeStoreAPI) and a self-managed MySQL database — switchable via Spring's dependency injection without touching the controller layer.

## Features

- Full CRUD operations (Create, Read, Update, Delete) on products
- Dual service implementation strategy using Spring's `@Qualifier`:
    - `FakeStoreProductService` — fetches/maps data from the external [FakeStoreAPI](https://fakestoreapi.com/)
    - `SelfProductServices` — manages products in a local MySQL database
- Clean layered architecture: Controller → Service (interface-driven) → Repository → Database
- Reusable JPA base entity (`BaseModel`) using `@MappedSuperclass` for automatic `createdAt` / `updatedAt` timestamping
- `@Embeddable` value object (`Rating`) for nested rating data
- DTO-to-Entity mapping for external API responses
- Centralized exception handling via `@RestControllerAdvice` for clean, consistent error responses
- Fully tested using Postman (CRUD + error scenarios)

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- RestTemplate (external API integration)
- Postman (testing)
- IntelliJ IDEA

## Project Structure

```
com.example.Project1
├── config            # RestTemplate bean configuration
├── controller        # REST controllers
├── controllerAdvice  # Global exception handling
├── dtos              # Data Transfer Objects (external API mapping)
├── exceptions        # Custom exceptions + error response model
├── models            # JPA entities (Product, Rating, BaseModel)
├── repository        # Spring Data JPA repositories
└── service           # Service interface + implementations
```

## API Endpoints

| Method | Endpoint              | Description                  |
|--------|------------------------|-------------------------------|
| GET    | `/products/hello`      | Health check                  |
| GET    | `/products`             | Get all products               |
| GET    | `/products/{id}`        | Get a product by ID            |
| POST   | `/products`              | Create a new product           |
| PUT    | `/products/{id}`        | Update an existing product     |
| DELETE | `/products/{id}`        | Delete a product               |

### Sample Request Body (POST/PUT)

```json
{
  "title": "Wireless Mouse",
  "description": "Ergonomic wireless mouse with USB receiver",
  "price": 799.0,
  "category": "Electronics",
  "imageUrl": "https://example.com/mouse.jpg",
  "rating": {
    "rate": 4.5,
    "count": 120
  }
}
```

## Getting Started

### Prerequisites

- Java 17+
- MySQL 8+
- Maven (or use the included `mvnw` wrapper)

### Setup

1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/product-management-api.git
   cd product-management-api
   ```

2. Create a MySQL database
   ```sql
   CREATE DATABASE project1;
   ```

3. Configure your database credentials in `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/project1
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. Run the application
   ```bash
   ./mvnw spring-boot:run
   ```

5. The API will be available at `http://localhost:8080`

## Testing

All endpoints were tested end-to-end using Postman, covering:
- Successful CRUD flows
- Data persistence verification in MySQL
- Error handling for invalid/missing resources (clean 404 responses via centralized exception handling)

## Future Improvements

- Add input validation (`@Valid` / Bean Validation)
- Add pagination and filtering (e.g., by category)
- Add Spring Security with JWT authentication
- Add unit and integration tests
- Add Swagger/OpenAPI documentation

## Author

Built by Elakiya A — B.Tech ECE student with an Honours specialization in Data Science.
