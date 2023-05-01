package com.travel_agency.controller;

import com.travel_agency.domain.dto.DestinationDTO;
import com.travel_agency.exception.DestinationNotFoundException;
import com.travel_agency.service.DestinationService;
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
    public ResponseEntity<List<DestinationDTO>> getDestinations() {
        return ResponseEntity.ok(destinationService.findDestinations());
    }

    @DeleteMapping(value = "{destinationId}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long destinationId) throws DestinationNotFoundException {
        destinationService.deleteDestination(destinationId);
        return ResponseEntity.ok().build();
    }
}
