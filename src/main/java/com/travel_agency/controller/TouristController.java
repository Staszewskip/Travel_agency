package com.travel_agency.controller;

import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.domain.dto.TouristDTOGet;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.service.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/tourists")
@RequiredArgsConstructor
public class TouristController {
    private final TouristService touristService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTourist(@RequestBody TouristDTO touristDTO) {
        touristService.saveTourist(touristDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TouristDTO> modifyTourist(@RequestBody TouristDTO touristDTO) throws TouristNotFoundException {
        return ResponseEntity.ok(touristService.modifyTourist(touristDTO));
    }

    @GetMapping(value = "{touristId}")
    public ResponseEntity<TouristDTOGet> getTourist(@PathVariable long touristId) throws TouristNotFoundException {
        return ResponseEntity.ok(touristService.findTourist(touristId));
    }

    @GetMapping
    public ResponseEntity<List<TouristDTOGet>> getAllTourists() {
        return ResponseEntity.ok(touristService.findAllTourists());
    }

    @DeleteMapping("{touristId}")
    public ResponseEntity<Void> deleteTourist(@PathVariable Long touristId) throws TouristNotFoundException {
        touristService.deleteTourist(touristId);
        return ResponseEntity.ok().build();
    }
}
