package com.tekarch.TafBookingService.Services.Interface;


import com.tekarch.TafBookingService.DTO.BookingRequestDTO;
import com.tekarch.TafBookingService.DTO.BookingResponseDTO;
import com.tekarch.TafBookingService.DTO.BookingsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    BookingResponseDTO addBooking(BookingRequestDTO requestDTO);
    BookingResponseDTO getBookingById(Long booking_id);
    List<BookingResponseDTO> getAllUserBookings(Long user_id);
    void deleteBooking(Long booking_id);
}
