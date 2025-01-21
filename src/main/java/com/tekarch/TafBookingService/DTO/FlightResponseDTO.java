package com.tekarch.TafBookingService.DTO;

import lombok.Data;

@Data
public class FlightResponseDTO {
    private Long flight_id;
    private String flight_number;
    private String departure;
    private String arrival;
    private Long availableSeats;
}
