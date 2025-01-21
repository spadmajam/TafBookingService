package com.tekarch.TafBookingService.Controllers;

import com.tekarch.TafBookingService.DTO.BookingRequestDTO;
import com.tekarch.TafBookingService.DTO.BookingResponseDTO;
import com.tekarch.TafBookingService.DTO.BookingsDTO;
import com.tekarch.TafBookingService.Services.BookingServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingsController {

    private static final Logger logger = LogManager.getLogger(BookingsController.class);

    @Autowired
    private BookingServiceImpl bookingService;

    //Get booking details by ID
    @GetMapping("/{booking_id}")
    public ResponseEntity<?> getBooking(@PathVariable Long booking_id) {
        try {
            BookingResponseDTO bookingResponse = bookingService.getBookingById(booking_id);
            return ResponseEntity.ok(bookingResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Create a new booking
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO request) {
       logger.info("BookingRequest: " + request);
        try {
            BookingResponseDTO bookingResponse = bookingService.addBooking(request);
            return ResponseEntity.ok(bookingResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Retrieve all bookings of a user by ID
    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getAllBookings(@PathVariable("user_id") Long user_id) {
        try {
            List<BookingResponseDTO> bookings = bookingService.getAllUserBookings(user_id);
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
       /* if(bookings.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }
*/
    }

    //Cancel a booking. Do not delete the record. Instead mark the status as cancelled
    @DeleteMapping("/{booking_id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long booking_id) {
        try {
            bookingService.deleteBooking(booking_id);
            return ResponseEntity.ok("Booking canceled successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
