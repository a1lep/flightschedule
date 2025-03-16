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

    private static final double SEAT_AVAILABILITY_MODIFY_PERCENTAGE = 30.0;
    private final SeatRepository seatRepository;

    public List<Seat> getAllSeats(final Long flightId) {
        return seatRepository.getAllSeats(flightId);
    }

    public List<List<Seat>> getAllSeatsByRow(final Long flightId) {
       final List<Seat> seats = seatRepository.getAllSeatsByRow(flightId);
        List<List<Seat>> seatsByRow = new ArrayList<>();

        int currentRow = -1;
        List<Seat> currentRowSeats = null;

        for (Seat seat : seats) {
            if (seat.row() != currentRow) {
                if (currentRowSeats != null) {
                    if (currentRow >= 1 && currentRow <= 20) {
                        seatsByRow.add(currentRowSeats);
                    }
                }
                currentRow = seat.row();
                currentRowSeats = new ArrayList<>();
            }
            seat = changeSeatAvailability(seat);

            currentRowSeats.add(seat);
        }

        if (currentRowSeats != null && currentRow >= 1 && currentRow <= 20) {
            seatsByRow.add(currentRowSeats);
        }

        return seatsByRow;
    }

    private Seat changeSeatAvailability(Seat seat) {
        // Use the configurable percentage to modify the seat availability
        if (Math.random() < SEAT_AVAILABILITY_MODIFY_PERCENTAGE / 100.0) {
            return new Seat(seat.id(), seat.flightId(), seat.seatNumber(), false, seat.seatClass(), seat.row(), seat.isWindow(), seat.isExtraLegroom(), seat.isCloseToExit());
        }
        return seat; // If not changed, return the seat as is
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
