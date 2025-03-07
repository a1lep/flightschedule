package com.cgi.Flights.controller;

import com.cgi.Flights.model.Seat;
import com.cgi.Flights.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/seats", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/allSeats")
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/isWindow/{seatId}")
    public boolean isSeatWindow(@PathVariable final Long seatId) {
        return seatService.isSeatWindow(seatId);
    }

    @GetMapping("/isExtraLegroom/{seatId}")
    public boolean isSeatExtraLegroom(@PathVariable final Long seatId) {
        return seatService.isSeatExtraLegroom(seatId);
    }

    @GetMapping("/isCloseToExit/{seatId}")
    public boolean isSeatCloseToExit(@PathVariable final Long seatId) {
        return seatService.isSeatCloseToExit(seatId);
    }

    @GetMapping("/isAvailable/{seatId}")
    public boolean isSeatAvailable(@PathVariable final Long seatId) {
        return seatService.isSeatAvailable(seatId);
    }

    @GetMapping("/availableSeats/{flightId}")
    public List<Seat> getAvailableSeats (@PathVariable final Long flightId) {
        return seatService.getAvailableSeats(flightId);
    }

    @PutMapping("/bookSeat/{seatId}")
    public void bookSeat (@PathVariable final Long seatId) throws Exception {
        seatService.bookSeat(seatId);
    }

}
