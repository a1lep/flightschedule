package com.cgi.Flights.model;

import java.sql.Date;
import java.sql.Time;


public record Flight(
        Long id,
        String flightNumber,
        String origin,
        String destination,
        Date departureDate,
        Time departureTime,
        Double flightDuration,
        Double price
) {
}