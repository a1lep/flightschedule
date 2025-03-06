package com.cgi.Flights.controller;

import com.cgi.Flights.model.Flight;
import com.cgi.Flights.model.FlightRequest;
import com.cgi.Flights.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/flights", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlightController {
    private final FlightService flightService;

    @GetMapping("/allFlights")
    public List<Flight> getFlights(){
        return flightService.getAllFlights();
    }

    @PostMapping("/addFlight")
    public void addFlight(@RequestBody final FlightRequest flightRequest){
        flightService.addFlight(flightRequest);
    }

    @GetMapping("/flightByDestination/{destination}")
    public List<Flight> getFlightByDestination(@PathVariable("destination") final String destination){
        return flightService.getFlightByDestination(destination);
    }

    @GetMapping("/flightByDuration/{duration}")
    public List<Flight> getFlightByDuration(@PathVariable("duration") final Double duration){
        return flightService.getFlightByDuration(duration);
    }

    @GetMapping("/flightByPrice/{priceFrom}/{priceTo}")
    public List<Flight> getFlightByPrice(@PathVariable ("priceFrom") final Double priceFrom,
                                         @PathVariable ("priceTo") final Double priceTo){
        return flightService.getFlightByPrice(priceFrom,priceTo);
    }

    @GetMapping("/flightByDate/{departureDate}")
    public List<Flight> getFlightByDate(@PathVariable("departureDate") final Date departureDate) {
        return flightService.getFlightByDate(departureDate);
    }

}
