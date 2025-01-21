package com.tekarch.TafBookingService.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private Long booking_id;
    private Long user_id;
    private Long flight_id;
    private String status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
