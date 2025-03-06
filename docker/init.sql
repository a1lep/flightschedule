CREATE TABLE IF NOT EXISTS flights (
                                       id SERIAL PRIMARY KEY,
                                       flight_number VARCHAR(20) NOT NULL,
    origin VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    departure_date DATE NOT NULL,
    departure_time TIME NOT NULL,
                                     flight_duration FLOAT NOT NULL,
                                     price DECIMAL(10,2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS seats (
    id SERIAL PRIMARY KEY,
                                     flight_id INT NOT NULL REFERENCES flights(id) ON DELETE CASCADE,
    seat_number VARCHAR(10) NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    seat_class VARCHAR(20) NOT NULL
    );
