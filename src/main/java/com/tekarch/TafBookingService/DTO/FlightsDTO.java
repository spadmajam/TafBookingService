package com.tekarch.TafBookingService.DTO;


import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class FlightsDTO {
    private Long flight_id;
    private String flight_number;
    private String departure;
    private String arrival;
    private LocalDateTime departure_time;
    private LocalDateTime arrival_time;
    private BigDecimal price;
    private Long available_seats;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
