package com.cgi.Flights.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


public record Flight(
        Long id,
        String flightNumber,
        String origin,
        String destination,
        ZonedDateTime departureTime,
        ZonedDateTime departureDate,
        float flightDuration,
        BigDecimal price
) {
}
