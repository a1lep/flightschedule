package com.cgi.Flights.repository.impl;

import com.cgi.Flights.model.Flight;
import com.cgi.Flights.model.FlightRequest;
import com.cgi.Flights.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FlightRepositoryImpl implements FlightRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Flight> getAllFlights() {
        final String sql = """
                SELECT id, flight_number, origin, destination, departure_date, departure_time, flight_duration, price
                FROM flights
                """;
        return jdbcTemplate.query(sql, (rs, _) -> mapFlight(rs));
    }

    private Flight mapFlight(final ResultSet rs) throws SQLException {
        return new Flight(
                rs.getLong("id"),
                rs.getString("flight_number"),
                rs.getString("origin"),
                rs.getString("destination"),
                rs.getDate("departure_date"),
                rs.getTime("departure_time"),
                rs.getDouble("flight_duration"),
                rs.getDouble("price")
        );
    }

    @Override
    public void addFlight(final FlightRequest request) {
        final String sql = """
                INSERT INTO flights(flight_number, origin, destination, departure_date,departure_time, flight_duration, price)
                VALUES(:flightNumber, :origin, :destination, :departureDate, :departureTime, :flightDuration, :price)
                """;
        jdbcTemplate.update(sql, Map.of(
                "flightNumber", request.flightNumber(),
                "origin", request.origin(),
                "destination", request.destination(),
                "departureDate", request.departureDate(),
                "departureTime", request.departureTime(),
                "flightDuration", request.flightDuration(),
                "price", request.price()));
    }

    @Override
    public List<Flight> getFlightByDestination (String destination) {
        final String sql = """
            SELECT id, flight_number, origin, destination, departure_date, departure_time, flight_duration, price
            FROM flights
            WHERE destination = :destination
            """;

        return jdbcTemplate.query(sql, Map.of("destination", destination), (rs, _) -> mapFlight(rs));
    }

    @Override
    public List<Flight> getFlightByDuration (Double flightDuration) {
        final String sql = """
            SELECT id, flight_number, origin, destination, departure_date, departure_time, flight_duration, price
            FROM flights
            WHERE flight_duration = :flightDuration
            """;
        return jdbcTemplate.query(sql, Map.of("flightDuration", flightDuration), (rs, _) -> mapFlight(rs));
    }

    @Override
    public List<Flight> getFlightByPrice (Double priceFrom, Double priceTo) {
        final String sql = """
            SELECT id, flight_number, origin, destination, departure_date, departure_time, flight_duration, price
            FROM flights
            WHERE price BETWEEN :priceFrom AND :priceTo
            """;
        return jdbcTemplate.query(sql, Map.of("priceFrom", priceFrom, "priceTo", priceTo), (rs, _) -> mapFlight(rs));
    }

    @Override
    public  List<Flight> getFlightByDate (Date departureDate) {
        final String sql = """
            SELECT id, flight_number, origin, destination, departure_date, departure_time, flight_duration, price
            FROM flights
            WHERE departure_date = :departureDate
            """;
        return jdbcTemplate.query(sql, Map.of("departureDate", departureDate), (rs, _) -> mapFlight(rs));

    }
}
