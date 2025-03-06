package com.cgi.Flights.repository;

import com.cgi.Flights.model.Flight;
import com.cgi.Flights.model.FlightRequest;

import java.sql.Date;
import java.util.List;

public interface FlightRepository {

    List<Flight> getAllFlights();
    void addFlight(FlightRequest flightRequest);
    List<Flight> getFlightByDestination(String destination);
    List<Flight> getFlightByDuration(Double duration);
    List<Flight> getFlightByPrice(Double priceFrom, Double priceTo);
    List<Flight> getFlightByDate(Date departureDate);
}
