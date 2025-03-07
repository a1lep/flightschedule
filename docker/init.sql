CREATE TABLE IF NOT EXISTS flights (
    id SERIAL PRIMARY KEY,
    flight_number VARCHAR(20) NOT NULL,
    origin VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    departure_date DATE NOT NULL,
    departure_time TIME NOT NULL,
    flight_duration DECIMAL(5,2) NOT NULL,
    price DECIMAL(10,2) NOT NULL
    );

CREATE TYPE seat_class_enum AS ENUM('ECONOMY','BUSINESS','FIRST');

CREATE TABLE IF NOT EXISTS seats (
    id SERIAL PRIMARY KEY,
    flight_id INT NOT NULL REFERENCES flights(id) ON DELETE CASCADE,
    seat_number VARCHAR(10) NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    seat_class seat_class_enum NOT NULL,
    seat_row INT NOT NULL,
    is_window BOOLEAN NOT NULL,
    is_extra_leg_room BOOLEAN NOT NULL,
    is_close_to_exit BOOLEAN NOT NULL
    );
