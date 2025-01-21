package com.tekarch.TafBookingService.Services;

import com.tekarch.TafBookingService.DTO.*;
import com.tekarch.TafBookingService.Exceptions.BookingNotFoundException;
import com.tekarch.TafBookingService.Services.Interface.BookingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BookingServiceImpl implements BookingService {
    private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    @Value("${tafdatastorems.url}")
    private String dataStoreMsUrl;

    /*@Override
    public BookingResponseDTO addBooking(BookingRequestDTO booking) {
        String url = dataStoreMsUrl + "/bookings";

        return restTemplate.postForObject(url, booking, BookingResponseDTO.class);
       *//* if (response.getStatus().equals(201)) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to create booking");
        }*//*
    }*/

  /*  @Override
    public BookingResponseDTO addBooking(BookingRequestDTO booking) {
        String url = dataStoreMsUrl + "/bookings";
        String userUrl = dataStoreMsUrl + "/users/";
        String flightUrl = dataStoreMsUrl + "/flights/";
        ResponseEntity<UsersDTO> usersDTO = restTemplate.getForEntity(userUrl + booking.getUser_id(),UsersDTO.class);
        ResponseEntity<FlightsDTO> flightsDTO = restTemplate.getForEntity(flightUrl+ booking.getFlight_id(),FlightsDTO.class);
        BookingResponseDTO newbooking = new BookingResponseDTO();
               newbooking.setUser_id(usersDTO.getBody().getId());
               newbooking.setFlight_id(flightsDTO.getBody().getFlight_id());

        //restTemplate.put(url,usersDTO);
        //restTemplate.put(url,flightsDTO);

        return restTemplate.postForObject(url, newbooking, BookingResponseDTO.class);

    }*/


    // updated one

    @Override
    public BookingResponseDTO addBooking(BookingRequestDTO booking) {
        String url = dataStoreMsUrl + "/bookings";
        String userUrl = dataStoreMsUrl + "/users/";
        String flightUrl = dataStoreMsUrl + "/flights/";

        // Log incoming request
        System.out.println("BookingRequestDTO: " + booking);

        // Fetch user details
        ResponseEntity<UsersDTO> usersDTO = restTemplate.getForEntity(userUrl + booking.getUser_id(), UsersDTO.class);
        if (usersDTO.getBody() == null) {
            throw new RuntimeException("User not found for ID: " + booking.getUser_id());
        }

        // Fetch flight details
        ResponseEntity<FlightsDTO> flightsDTO = restTemplate.getForEntity(flightUrl + booking.getFlight_id(), FlightsDTO.class);
        if (flightsDTO.getBody() == null) {
            throw new RuntimeException("Flight not found for ID: " + booking.getFlight_id());
        }

        // Prepare booking response
        BookingResponseDTO newBooking = new BookingResponseDTO();
        newBooking.setUser_id(usersDTO.getBody().getId());
        newBooking.setFlight_id(flightsDTO.getBody().getFlight_id());

        // Log the booking details
        System.out.println("Booking to save: " + newBooking);

        // Save booking via DatastoreService
        return restTemplate.postForObject(url, newBooking, BookingResponseDTO.class);
    }




    @Override
    public BookingResponseDTO getBookingById(Long booking_id) {
        String url = dataStoreMsUrl + "/bookings/" + booking_id;

        //return restTemplate.getForObject(url,BookingResponseDTO.class);
        ResponseEntity<BookingResponseDTO> response = restTemplate.getForEntity(url, BookingResponseDTO.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new BookingNotFoundException("Booking not found with ID: " + booking_id);
        }
    }

    @Override
    public List<BookingResponseDTO> getAllUserBookings(@PathVariable Long user_id) {
        String url = dataStoreMsUrl + "/bookings/user/"+ "id";
        //ResponseEntity<BookingsDTO[]> response = restTemplate.getForEntity(url, BookingsDTO[].class);
        return Arrays.asList(restTemplate.getForObject(url, BookingResponseDTO[].class));
       /* if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException("Failed to fetch bookings for the user");
        }*/
    }

    @Override
    public void deleteBooking(Long booking_id) {
        String url = dataStoreMsUrl + "/bookings/" + booking_id;
        restTemplate.delete(url);
    }

   /* //Get flight by ID
    @Override
    public BookingsDTO getFlightById(Long flight_id) {
        String url = dataStoreMsUrl + "/flights/" + flight_id;
        ResponseEntity<FlightsDTO> response = restTemplate.getForEntity(url, FlightsDTO.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new FlightNotFoundException("Flight not found with ID: " + flight_id);
        }
    }

    //Get all flights
    public List<FlightsDTO> getAllFlights() {
        String url = flightRepoUrl + "/flights";
        ResponseEntity<FlightsDTO[]> response = restTemplate.getForEntity(url, FlightsDTO[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException("Failed to fetch flights");
        }
    }

    // Create a new flight
    public FlightsDTO addFlight(FlightsDTO flightDTO) {
        String url = flightRepoUrl + "/flights";
        ResponseEntity<FlightsDTO> response = restTemplate.postForEntity(url, flightDTO, FlightsDTO.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to create flight");
        }
    }

    // Update an existing flight by ID
    public FlightsDTO updateFlightById(Long flight_id, FlightsDTO flightDTO) {
        String url = flightRepoUrl + "/flights/" + flight_id;
        restTemplate.put(url, flightDTO);
        return getFlightById(flight_id);
    }

    // Delete a flight by ID
    public void deleteFlight(Long flight_id) {
        String url = flightRepoUrl + "/flights/" + flight_id;
        restTemplate.delete(url);
    }*/
}
