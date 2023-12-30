# API Documentation

## Introduction

This API documentation showcases various functionalities implemented using Java and Spring Boot within a limited timeframe of four hours. While the focus has been on demonstrating endpoint functionalities, it's important to note that due to time constraints, functional tests have not been included. However, I am well-versed in testing tools like JUnit, Mockito, and Cucumber, which can be leveraged for comprehensive test coverage in a production environment.

I've also attached the postman collection associated with the project, `Hostfully-Technical-Test.postman_collection.json`.

To run the project, ``mvn clean install`` install and execute the jar in target folder.

The project starts with a H2 DB with an user: `fabian:fabian`

## Auth Controller

- **Register User:**
    - `POST /auth/register`: Registers a new user.

- **Authenticate User:**
    - `POST /auth`: Authenticates a user.

## Block Controller

- **Get Block:**
    - `GET /block/{id}`: Retrieves block details by ID.

- **Create Block:**
    - `POST /block`: Creates a new block.

- **Update Block:**
    - `PUT /block`: Updates an existing block.

## Booking Controller

- **Get Booking:**
    - `GET /booking/{id}`: Retrieves booking details by ID.

- **Create Booking:**
    - `POST /booking`: Creates a new booking.

- **Update Booking:**
    - `PUT /booking`: Updates an existing booking, updating a cancelled booking automatically rebooks.

- **Cancel Booking:**
    - `GET /booking/{id}/cancel`: Cancels a booking.

- **Re-book Booking:**
    - `GET /booking/{id}/book`: Re-books a canceled booking.

## Property Controller

- **Get Property:**
    - `GET /property/{id}`: Retrieves property details by ID.

- **Create Property:**
    - `POST /property`: Creates a new property.

- **Update Property:**
    - `PUT /property`: Updates an existing property.
