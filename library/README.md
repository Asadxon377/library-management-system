# Library Management System

A full-stack Library Management System built with Java and Spring Boot. The application allows users to manage books, authors, and categories with role-based access control.

## Features

- Manage books, authors, and categories
- Create, update, and delete records
- Book availability tracking
- Author and category relationships with books
- Role-based authorization using Spring Security
- Database-backed user authentication
- DTO pattern with separate Request and Response DTOs
- Input validation
- Global exception handling
- Custom business exceptions
- Custom error pages
- Access denied page
- Server-side rendering with Thymeleaf

## Roles and Permissions

Role & Permissions

| USER | View books, authors, and categories |
| MANAGER | USER permissions + update books, authors, and categories |
| ADMIN | MANAGER permissions + delete books, authors, and categories |

## Technologies Used

- Java 25
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Hibernate
- MySQL
- Thymeleaf
- Bootstrap
- Maven
- Lombok

## Project Architecture

src/main/java/com/gpt/library
│
├── controller        # Handles HTTP requests and responses
├── dto
│   ├── request       # Incoming data transfer objects
│   └── response      # Outgoing data transfer objects
├── entity            # JPA entities
├── exception         # Custom exceptions and global exception handler
├── mapper            # Entity to DTO mapping
├── repository        # Database access layer
├── security          # Spring Security configuration
└── service           # Business logic layer
