package com.cgi.Flights.repository;

import com.cgi.Flights.model.Seat;

import java.util.List;

public interface SeatRepository {

    List<Seat> getAllSeats();
    boolean isSeatWindow(Long seatId);
    boolean isSeatExtraLegroom(Long seatId);
    boolean isSeatCloseToExit(Long seatId);
    List<Seat> getAvailableSeatsByFlight(Long flightId);
    void bookSeat(Long seatId);
    boolean isSeatAvailable(Long seatId);
}
