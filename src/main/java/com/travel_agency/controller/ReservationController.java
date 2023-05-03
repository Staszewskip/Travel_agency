package com.travel_agency.controller;

import com.travel_agency.domain.dto.ReservationDTO;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.domain.dto.TouristGuestDTO;
import com.travel_agency.exception.ReservationNotFoundException;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addReservation(@RequestBody ReservationDTO reservationDTO) throws TouristNotFoundException {
        reservationService.saveReservation(reservationDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = {"reservationId"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTouristGuestToReservation(@PathVariable Long reservationId, @RequestBody TouristGuestDTO touristGuestDTO) throws ReservationNotFoundException {
        reservationService.addTouristsReservation(reservationId, touristGuestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> modifyReservation(@RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        return ResponseEntity.ok(reservationService.showReservations());
    }

    @DeleteMapping("{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) throws ReservationNotFoundException {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok().build();
    }
}
