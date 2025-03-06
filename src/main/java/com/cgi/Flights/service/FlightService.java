package com.cgi.Flights.service;

import com.cgi.Flights.model.Flight;
import com.cgi.Flights.model.FlightRequest;
import com.cgi.Flights.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

   private final FlightRepository flightRepository;

   public List<Flight> getAllFlights() {
      return flightRepository.getAllFlights();
   }

   public void addFlight(final FlightRequest flightRequest) {
      flightRepository.addFlight(flightRequest);
   }

   public List<Flight> getFlightByDestination(final String destination) {
      return flightRepository.getFlightByDestination(destination);
   }

   public List<Flight> getFlightByDuration(final Double duration) {
      return flightRepository.getFlightByDuration(duration);
   }

   public List<Flight> getFlightByPrice (final Double priceFrom, final Double priceTo) {
      return flightRepository.getFlightByPrice(priceFrom,priceTo);
   }

   public List<Flight>getFlightByDate (final Date departureDate) {
      return flightRepository.getFlightByDate(departureDate);
   }
}
