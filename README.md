# Authentication System with Spring Security and JWT

This repository contains an implementation of a robust authentication system built with **Spring Security** and **JWT (JSON Web Tokens)**. It includes features for user authentication, email verification, and token-based security.

---

## Features

- **Spring Security Integration**: Provides a secure framework for user authentication and role-based authorization.
- **JWT-Based Authentication**:
  - Secure token generation for user login.
  - Stateless authentication using access tokens.
- **Email Verification**:
  - Users receive a verification email upon registration.
  - Email includes a verification code that expires after a specified duration.
  - Unverified accounts are restricted from accessing protected resources.
- **Custom Exceptions**: Provides detailed error messages for various scenarios (e.g., user not found, account not verified).
- **Database Integration**: 
  - User details are securely stored in a database.
  - Email and username are unique fields to prevent duplication.
- **Scalable Design**: Modular and extensible, allowing for future features like password recovery or multi-factor authentication.

---

## Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Framework for building the application.
- **Spring Security**: Handles authentication and authorization.
- **JWT**: Provides stateless, token-based authentication.
- **Hibernate/JPA**: For database interaction and object-relational mapping.
- **MySQL**: Database for storing user information.
- **Jakarta Mail**: For sending verification emails.

