package com.travel_agency.controller;

import com.travel_agency.domain.dto.DestinationDTO;
import com.travel_agency.domain.dto.get.DestinationDTOGet;
import com.travel_agency.domain.dto.weather.WeatherDTO;
import com.travel_agency.exception.DestinationNotFoundException;
import com.travel_agency.service.DestinationService;
import com.travel_agency.service.WeatherService;
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
@RequestMapping("v1/destinations")
@RequiredArgsConstructor
public class DestinationController {
    private final DestinationService destinationService;
    private final WeatherService weatherService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addDestination(@RequestBody DestinationDTO destinationDTO) {
        destinationService.saveDestination(destinationDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DestinationDTO> modifyDestination(@RequestBody DestinationDTO destinationDTO) throws DestinationNotFoundException {
        return ResponseEntity.ok(destinationService.modifyDestination(destinationDTO));
    }

    @GetMapping
    public ResponseEntity<List<DestinationDTOGet>> getDestinations() {
        return ResponseEntity.ok(destinationService.getDestinations());
    }
    @Operation(summary = "Showing all users - for admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All users from database", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = "admin")
    public ResponseEntity<List<DestinationDTO>> adminShowDestinations() {
        return ResponseEntity.ok(destinationService.showDestinations());
    }

    @DeleteMapping(value = "{destinationId}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long destinationId) throws DestinationNotFoundException {
        destinationService.deleteDestination(destinationId);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Checking weather for given location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Weather for given location", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = "weather/{location}")
    public ResponseEntity<WeatherDTO> getForecast(@PathVariable String location)  {
        return weatherService.getForecast(location);
    }
}
