package com.cgi.Flights.model;

public record Seats(
        Long id,
        Long flightId,
        String seatNumber,
        boolean isAvailable,
        String seatClass
) {
}
