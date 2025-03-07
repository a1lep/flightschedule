package com.cgi.Flights.service;

import com.cgi.Flights.model.Seat;
import com.cgi.Flights.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    public List<Seat> getAllSeats() {
        return seatRepository.getAllSeats();
    }

    public boolean isSeatWindow(final Long seatId) {
        return seatRepository.isSeatWindow(seatId);
    }

    public boolean isSeatExtraLegroom(final Long seatId) {
        return seatRepository.isSeatExtraLegroom(seatId);
    }

    public boolean isSeatCloseToExit(final Long seatId) {
        return  seatRepository.isSeatCloseToExit(seatId);
    }

    public boolean isSeatAvailable(final Long seatId) {
        return seatRepository.isSeatAvailable(seatId);
    }

    public List<Seat> getAvailableSeats(final Long flightId) {
        List<Seat> availableSeatsByFlight = seatRepository.getAvailableSeatsByFlight(flightId);
        return getRandomSeats(availableSeatsByFlight);
    }

    private List<Seat> getRandomSeats (final List<Seat> seats) {
        Random random = new Random();
        double percentage = 50+ random.nextInt(31);
        ArrayList<Seat> copySeats = new ArrayList<>(seats);
        Collections.shuffle(copySeats);
        int size = (int)(seats.size() * (percentage / 100.0));
        return copySeats.subList(0,size);
    }

    public void bookSeat (final Long seatId) throws Exception {
        if (isSeatAvailable(seatId)) {
            seatRepository.bookSeat(seatId);
        } else {
            throw new Exception("The seat you're trying to book is not available, seat ID: " + seatId);
        }
    }


}
