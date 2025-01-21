/*
package com.tekarch.TafBookingService.Controllers;

import com.tekarch.TafFlightService.DTO.FlightsDTO;
import com.tekarch.TafFlightService.Services.FlightServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@AllArgsConstructor
public class BookingsController_1 {

    @Autowired
    private FlightServiceImpl flightServiceImpl;

    //Get flight by ID
    @GetMapping("/{flight_id}")
    public ResponseEntity<FlightsDTO> getFlight(@PathVariable("flight_id") Long flight_id) {
        FlightsDTO flight = flightServiceImpl.getFlightById(flight_id);
        if(flight==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight,HttpStatus.OK);
        //return ResponseEntity.ok(flight);
    }

    // Get all flights
    @GetMapping
    public ResponseEntity<List<FlightsDTO>> getAllFlights() {
        List<FlightsDTO> flights = flightServiceImpl.getAllFlights();
        return new ResponseEntity<>(flights,HttpStatus.OK);
        // return ResponseEntity.ok(flights);
    }
    // Create a new flight
    @PostMapping
    public ResponseEntity<FlightsDTO> createFlight(@RequestBody FlightsDTO flightDTO) {
        FlightsDTO createdFlight = flightServiceImpl.addFlight(flightDTO);
        return new ResponseEntity<>(createdFlight,HttpStatus.CREATED);
        //return ResponseEntity.ok(createdFlight);
    }

    // Update an existing flight
    @PutMapping("/{flight_id}")
    public ResponseEntity<FlightsDTO> updateFlight(@PathVariable("flight_id") Long flight_id, @RequestBody FlightsDTO flightDTO) {
        FlightsDTO updatedFlight = flightServiceImpl.updateFlightById(flight_id, flightDTO);
        //return ResponseEntity.ok(updatedFlight);
        return new ResponseEntity<>(updatedFlight,HttpStatus.OK);
    }

    // Delete a flight by ID
    @DeleteMapping("/{flight_id}")
    public ResponseEntity<String> deleteFlight(@PathVariable("flight_id") Long flight_id) {
        if(!flightServiceImpl.getFlightById(flight_id).getFlight_id().equals(0L)) {
            flightServiceImpl.deleteFlight(flight_id);
            return ResponseEntity.ok("Flight deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found.");
    }
}*/
