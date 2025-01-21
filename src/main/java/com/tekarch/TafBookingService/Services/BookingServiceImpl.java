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

        // Adding validation  code
        logger.info("Checking flight availability:: ");
        String flightIdUrl = flightUrl+ booking.getFlight_id();
        FlightsDTO flight = restTemplate.getForObject(flightIdUrl, FlightsDTO.class);
        if(flight.getAvailable_seats()<=0)
        {
            throw new RuntimeException("Flight is fully booked. No seats available. ");
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
        ResponseEntity<BookingResponseDTO> response = restTemplate.getForEntity(url, BookingResponseDTO.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new BookingNotFoundException("Booking not found with ID: " + booking_id);
        }
    }

    @Override
    public List<BookingResponseDTO> getAllUserBookings(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user_id: " + userId);
        }
        String url = dataStoreMsUrl + "/bookings/user/"+ userId;
        logger.info("Fetching bookings for userId: {}",userId);
        logger.info("Request URL: {}", url);
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
}
