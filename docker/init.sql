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

INSERT INTO flights (flight_number, origin, destination, departure_date, departure_time, flight_duration, price)
VALUES
    ('FL123', 'Tallinn', 'Paris', '2025-06-10', '08:00', 4.5, 150.00),
    ('FL456', 'Paris', 'Rome', '2025-06-11', '09:30', 2.5, 60.00),
    ('FL789', 'Tallinn', 'Barcelona', '2025-06-12', '14:45', 5.5, 450.00)

    INSERT INTO seats (flight_id, seat_number, seat_class, seat_row, is_window, is_extra_leg_room, is_close_to_exit)
SELECT
    f.id AS flight_id,
    s.letter AS seat_number,
    CASE
        WHEN r.row_number = 1 THEN 'FIRST'
        WHEN r.row_number BETWEEN 2 AND 5 THEN 'BUSINESS'
        ELSE 'ECONOMY'
        END::seat_class_enum AS seat_class,
        r.row_number AS seat_row,
    s.letter IN ('A', 'F') AS is_window,
    r.row_number IN (11) AS is_extra_leg_room,
    r.row_number IN (1, 10, 11, 20) AS is_close_to_exit
FROM flights f
         CROSS JOIN (SELECT GENERATE_SERIES(1, 20) AS row_number) r
         CROSS JOIN LATERAL (
    SELECT UNNEST(
                   CASE
                       WHEN r.row_number BETWEEN 1 AND 5 THEN ARRAY['A', 'C', 'D', 'F'] -- FIRST & BUSINESS (4 seats)
                       ELSE ARRAY['A', 'B', 'C', 'D', 'E', 'F'] -- ECONOMY (6 seats)
                       END
           ) AS letter) s;