package com.tekarch.TafBookingService.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequestDTO {
    private Long user_id;
    private Long flight_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
