# Flight Schedule - Back-end

## Overview

This part of the project is a back-end service for managing flight schedules and seat reservations. It provides APIs to fetch flight data, manage seat availability, and support a front-end interface. The back-end is structured into three layers: Repository, Service, and Controller.

Backend is running on localhost:8080.
## Pre requirements

To run the back-end, Docker must be installed and running on your machine. If you do not have Docker installed, download and install it from the following link: https://www.docker.com/products/docker-desktop/

Once Docker is installed and running, start the backend by executing the following command in your terminal:

```shell
docker-compose up -d
```

## Database Configuration

The back-end uses a PostgreSQL database configured via Docker. The docker-compose file includes:

- A PostgreSQL server instance.
- An initialization script that creates necessary tables and populates them with sample data for UI testing.

## Project Structure

### Repository Layer

- Uses JdbcTemplate to execute SQL queries and retrieve data from the database.
- JdbcTemplate was chosen over JPA for better security and to demonstrate SQL querying skills.

### Service Layer

- Fetches data from the repository and processes it before sending it to the controller.
- Converts seat data into a 2D list for easier handling in the frontend.
- Most methods simply retrieve data and pass it to the API without major modifications.

### Controller Layer

- Exposes APIs to the front-end.

- Fetches data from the service layer and makes it available for UI consumption.

## Improvements

- The project should have separate models for the repository and service/controller layers.

- The controller currently lacks security measures such as Spring Security, making the APIs vulnerable. User authentication should be implemented to restrict access and support secure seat booking.

- The project does not include user management, which would have provided a more complete experience by allowing proper seat reservations and secure access controls.

## Development Process

The back-end development took approximately 7-10 hours. Some changes were made during UI development to adjust API responses as needed.

## Challenges

- While overall development was straightforward, working with JdbcTemplate and the Record class was new for me, requiring additional research.

- Some errors encountered during development were resolved using online resources (were related working with jdbcTemplate).

- Used a previous project as a reference: https://github.com/a1lep/boardgame-project

## Related Repositories

The front-end UI for this project can be found here: https://github.com/a1lep/flightschedule-ui