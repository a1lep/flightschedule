package com.cgi.Flights.model;

public record Seat(
        Long id,
        Long flightId,
        String seatNumber,
        boolean isAvailable,
        SeatClass seatClass,
        Integer row,
        boolean isWindow,
        boolean isExtraLegroom,
        boolean isCloseToExit
) {
}
