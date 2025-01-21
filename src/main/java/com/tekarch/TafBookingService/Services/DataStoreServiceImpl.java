//package com.tekarch.TafBookingService.Services;
//
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class DataStoreServiceImpl {
///*
//    private final BookingRepository bookingRepository;
//    private final FlightRepository flightRepository;
//    private final UserRepository userRepository;*/
//
//    @Transactional
//    public Bookings createBooking(BookingRequest request)
//    {
//        // Validate User
//        Users user = userRepository.findById(request.getUser_id())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
//
//        // Validate Flight
//        Flights flight = flightRepository.findById(request.getFlight_id())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid flight ID"));
//
//        // Ensure there are available seats
//        if (flight.getAvailable_seats() <= 0) {
//            throw new IllegalStateException("No available seats on this flight");
//        }
//
//        // Create a new booking
//        Bookings booking = new Bookings();
//        booking.setUser(user);
//        booking.setFlight(flight);
//        booking.setStatus("Booked");
//      /*  booking.setCreated_at(new Date());
//        booking.setUpdated_at(new Date());*/
//
//        // Save the booking to the database
//        Bookings savedBooking = bookingRepository.save(booking);
//
//        // Update the flight's available seats
//        flight.setAvailable_seats(flight.getAvailable_seats() - 1);
//        flightRepository.save(flight);
//
//        return savedBooking;
//    }
//
//    // Retrieve all bookings for a user
//    public List<Bookings> getUserBookings(Long userId)
//    {
//        return bookingRepository.findByUser_Id(userId);
//    }
//
//    //Cancel the booking. Set the status as "Cancel"
//    public boolean cancelBooking(Long booking_id)
//    {
//        // Check if the booking exists
//        Optional<Bookings> bookingOptional = bookingRepository.findById(booking_id);
//
//        if (bookingOptional.isPresent()) {
//            // Update the status to "Cancelled"
//            Bookings booking = bookingOptional.get();
//            booking.setStatus("Cancelled");
//            bookingRepository.save(booking); // Save the updated booking
//            return true;
//        }
//        return false; // If booking doesn't exist
//    }
//    }
//
//
//
