# Spring Boot Travel Agency Learning Project - Context

## Project Overview

The project started as an e-learning website but was changed to a Travel Agency application.

Domain:
- Travel Packages
- Hotel Booking (future)
- Vehicle Booking (future)
- User Accounts
- Authentication & Authorization
- Booking System (next major feature)

Repository:
- Git initialized
- GitHub remote configured
- Maven project generated from Spring Initializr
- Spring Boot version: 4.0.5
- Java version: 25
- Build tool: Maven

---

# Learning Progress So Far

## Maven

Topics covered:

- pom.xml
- mvn vs mvnw
- Maven Wrapper
- .m2 repository
- Dependency resolution
- Local repository sharing between projects

Key understanding:

- mvnw still uses the same ~/.m2 repository.
- Dependencies are not duplicated per project.
- mvnw downloads Maven itself if necessary.
- mvn uses system-installed Maven.
- mvnw uses project-specific wrapper version.

---

# Spring Boot Project Structure

Learned:

src/main/java
src/main/resources
application.properties
target/
mvnw
pom.xml

Main class:

TravelAgencyApplication.java

Not Main.java.

---

# Travel Package Module

Entity:

TravelPackage

Current fields:

- id
- name
- description
- price
- duration (String)

Repository:

TravelPackageRepository extends JpaRepository

Service:

TravelPackageService

Controller:

TravelPackageController

---

# CRUD APIs Implemented

## GET package by id

Implemented.

Returns DTO.

Throws custom exception when package not found.

---

## GET all packages

Implemented.

Returns response DTO list.

Pagination and filtering added.

---

## POST package

Implemented.

Validation enabled.

Uses DTO.

ID auto-generated.

---

## PUT package

Implemented.

Full update.

---

## PATCH package

Implemented.

Partial update.

Discussion covered:
- difference between PUT and PATCH
- when PATCH is preferred

---

## DELETE package

Implemented.

Uses exception instead of boolean result.

---

# DTO Layer

Implemented:

- TravelPackageRequestDto
- TravelPackageResponseDto

Concepts learned:

- DTO purpose
- request vs response DTO
- security benefits
- hiding internal entity fields
- preventing over-posting
- avoiding circular serialization issues

---

# Validation

Implemented using:

- @Valid
- @NotBlank
- @NotNull
- validation annotations

Discussed:

Difference:

@NotNull
- value cannot be null

@NotBlank
- value cannot be null
- cannot be empty
- cannot contain only spaces

---

# Exception Handling

Implemented:

GlobalExceptionHandler

Using:

@ControllerAdvice

Custom exceptions:

- TravelPackageNotFoundException
- InvalidUsernamePasswordException

Concepts learned:

- exception bubbling
- global exception handling
- returning clean API responses
- proper HTTP status codes

---

# Database Layer

Current database:

H2

Configuration:

spring.datasource.url=jdbc:h2:mem:testdb

Current limitation:

Data disappears after restart.

H2 Console issue exists and was intentionally skipped because of Spring Boot 4 compatibility friction.

Future plan:

Move to SQLite instead of PostgreSQL because of laptop hardware constraints.

SQLite chosen because:

- lightweight
- persistent
- enough for learning JPA/Hibernate
- no separate DB server required

Next chat should help configure SQLite.

---

# JPA / Hibernate Concepts Learned

- @Entity
- @Id
- @GeneratedValue
- Repository pattern
- JpaRepository
- CRUD operations

Interesting observation:

Deleting ID=2 and creating a new row produced ID=3.

Reason:

Auto-increment sequences do not reuse deleted IDs.

---

# Spring Beans

Learned:

Bean:
- object managed by Spring

Dependency Injection:
- constructor injection
- field injection

Important note:

User learned that constructor injection is preferred over field injection.

---

# Authentication

Implemented.

## User Entity

Fields:

- id
- username
- password
- firstName
- lastName
- age
- phoneNumber
- email
- role

Role enum:

- USER
- ADMIN

Stored as:

EnumType.STRING

Reason discussed:

Enum ordinal can break authorization if enum order changes.

---

# Registration

Implemented.

Security decision:

Role is NOT accepted from request.

Service hardcodes:

user.setRole(Role.USER)

This prevents privilege escalation.

---

# Password Hashing

Implemented:

PasswordEncoder
BCrypt

Concepts learned:

- hashing vs encryption
- salting
- brute force resistance
- BCrypt

Important concept:

passwordEncoder.matches(raw, encoded)

instead of

passwordEncoder.encode(raw).equals(encoded)

because BCrypt uses random salt.

---

# Login

Implemented.

Flow:

- username lookup
- BCrypt password validation
- JWT generation

Invalid credentials:

Throws InvalidUsernamePasswordException

Handled globally.

Security discussion:

Never reveal whether username or password was incorrect.

---

# JWT

Implemented.

Topics learned:

JWT Structure:

Header
Payload
Signature

Claims

Expiration

Signature validation

Why JWT is stateless

Pros:
- scalable
- no session storage

Cons:
- logout harder
- stolen token usable until expiry

---

# JWT Service

Implemented.

User discovered:

- verifyWith() requires SecretKey
- older Key imports cause issues
- signWith(key, algorithm) deprecation

Newer JJWT APIs preferred.

---

# Spring Security

Implemented.

SecurityFilterChain configured.

Learned:

- request interception
- authentication vs authorization
- filter chain

Default Spring Security behavior:

- login page generated automatically
- default password generated

---

# JWT Authentication Filter

Implemented.

Flow:

Request
-> JWT Filter
-> Extract token
-> Validate token
-> Load user
-> SecurityContext
-> Controller

Class:

JwtAuthenticationFilter extends OncePerRequestFilter

---

# SecurityContext

Major learning point.

Authentication stored via:

SecurityContextHolder.getContext().setAuthentication(...)

Without this:
- request remains anonymous
- protected APIs fail

---

# UserDetailsService

Implemented:

CustomUserDetailsService

Loads user from database.

Creates:

UserDetails

with:

ROLE_USER
ROLE_ADMIN

---

# Authorization

Implemented.

SecurityConfig:

@EnableMethodSecurity

Controller protection:

@PreAuthorize("hasRole('ADMIN')")

Applied to:

- POST package
- PUT package
- PATCH package
- DELETE package

---

# Role vs Authority

Learned:

Spring internally works with authorities.

Examples:

ROLE_ADMIN
ROLE_USER

Difference:

hasRole("ADMIN")
-> checks ROLE_ADMIN

hasAuthority("ADMIN")
-> checks ADMIN exactly

---

# Admin Creation

Recommended implementation:

DataInitializer using CommandLineRunner

Creates:

username: admin
password: admin123
role: ADMIN

if admin account does not already exist.

User may or may not have implemented this yet.

Verify in next chat.

---

# /me Endpoint

User changed endpoint to:

GET /api/v1/users/me

Discussion:

Authentication object injection.

Current recommendation:

Use username from Authentication and query DB.

Avoid storing entire User entity in SecurityContext for now.

Reason:

Avoid stale principal data.

---

# Interview Topics Already Covered

User answered questions on:

- REST APIs
- PUT vs PATCH
- DTOs
- Dependency Injection
- Beans
- BCrypt
- JWT
- SecurityContext
- Authorization
- UserDetailsService
- Stateless Authentication
- 401 vs 403
- Roles vs Authorities

Knowledge gaps that were explained:

- refresh tokens
- logout in JWT systems
- HttpOnly cookies
- XSS
- CSRF
- stale JWT authorization data
- Authentication vs Authorization

---

# Current Architecture

Layers:

Controller
-> Service
-> Repository
-> Database

Security:

JWT
-> JWT Filter
-> SecurityContext
-> Controller

Authorization:

@PreAuthorize

Database:

Currently H2 in-memory.

Planned migration:

H2 -> SQLite

---

# Recommended Next Steps

1. Configure SQLite database.
2. Verify admin bootstrap user.
3. Verify /api/v1/users/me endpoint.
4. Create Booking entity.

Booking design:

User 1 ---- * Booking * ---- 1 TravelPackage

Learn:

- @ManyToOne
- @OneToMany
- foreign keys
- ownership authorization
- authenticated user access
- business validations

Future APIs:

POST /api/v1/bookings

GET /api/v1/bookings/my

DELETE /api/v1/bookings/{id}

Security rule:

Users can only access their own bookings.

Admins can access all bookings.

This is the next major learning milestone.
