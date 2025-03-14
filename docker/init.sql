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
    r.row_number IN (10, 11) AS is_extra_leg_room,
    r.row_number IN (1, 10, 11, 20) AS is_close_to_exit
FROM
    (SELECT UNNEST(ARRAY[1,2,3,4,5]) AS id) f,
    (SELECT GENERATE_SERIES(1, 20) AS row_number) r,
    (SELECT UNNEST(ARRAY['A','B','C','D','E','F']) AS letter) s;