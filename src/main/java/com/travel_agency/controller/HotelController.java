package com.travel_agency.controller;

import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.domain.dto.get.HotelDTOGet;
import com.travel_agency.exception.DestinationNotFoundException;
import com.travel_agency.exception.HotelNotFoundException;
import com.travel_agency.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/hotels")
@RequiredArgsConstructor
public class HotelController {
private final HotelService hotelService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addHotel(@RequestBody HotelDTO hotelDTO) throws DestinationNotFoundException {
        hotelService.saveHotel(hotelDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelDTO> modifyHotel(@RequestBody HotelDTO hotelDTO) throws HotelNotFoundException {
        return ResponseEntity.ok(hotelService.modifyHotel(hotelDTO));
    }

    @GetMapping
    public ResponseEntity<List<HotelDTOGet>> getHotels() {
        return ResponseEntity.ok(hotelService.getHotels());
    }

    @Operation(summary = "Showing all hotels - for admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All hotels from database", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = "admin")
    public ResponseEntity<List<HotelDTO>> showHotels() {
        return ResponseEntity.ok(hotelService.showHotels());
    }

    @DeleteMapping("{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) throws HotelNotFoundException {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok().build();
    }
}
