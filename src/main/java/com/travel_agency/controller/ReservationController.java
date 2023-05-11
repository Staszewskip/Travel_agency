package com.travel_agency.controller;

import com.travel_agency.domain.dto.ReservationDTO;
import com.travel_agency.domain.dto.TouristGuestDTO;
import com.travel_agency.domain.dto.get.ReservationDTOGet;
import com.travel_agency.exception.HotelNotFoundException;
import com.travel_agency.exception.ReservationNotFoundException;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addReservation(@RequestBody ReservationDTO reservationDTO) throws TouristNotFoundException, HotelNotFoundException {
        reservationService.saveReservation(reservationDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Add guest to the reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User added to the reservation", content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping(value = "{reservationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTouristGuestToReservation(@PathVariable Long reservationId, @RequestBody TouristGuestDTO touristGuestDTO) throws ReservationNotFoundException {
        reservationService.addTouristsReservation(reservationId, touristGuestDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Modification of existing reservation. Check-in and check-out date possible")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All reservations from database", content = {@Content(mediaType = "application/json")}),
    })
    @PutMapping(value = "{reservationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTOGet> modifyReservation(@PathVariable Long reservationId, @RequestBody LocalDate checkIn, LocalDate checkOut) throws ReservationNotFoundException {
        return ResponseEntity.ok(reservationService.modifyReservation(reservationId, checkIn, checkOut));
    }

    @Operation(summary = "Showing all reservations of given user. Firstname & lastname required")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All reservations from database", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = "user")
    public ResponseEntity<List<ReservationDTOGet>> getReservations(@RequestParam String firstname, @RequestParam String lastname) {
        return ResponseEntity.ok(reservationService.getReservationsOfGivenUser(firstname, lastname));
    }

    @Operation(summary = "Showing all reservations - for admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All reservations from database", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = "admin")
    public ResponseEntity<List<ReservationDTOGet>> showReservations() {
        return ResponseEntity.ok(reservationService.showReservations());
    }

    @DeleteMapping("{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) throws ReservationNotFoundException {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok().build();
    }
}
