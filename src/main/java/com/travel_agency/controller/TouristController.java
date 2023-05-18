package com.travel_agency.controller;

import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.domain.dto.TouristLoggedDTO;
import com.travel_agency.domain.dto.TouristLoggingDTO;
import com.travel_agency.domain.dto.get.TouristDTOGet;
import com.travel_agency.exception.LoginAlreadyUsedException;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.exception.WrongPasswordException;
import com.travel_agency.service.TouristService;
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
@RequestMapping("v1/tourists")
@RequiredArgsConstructor
public class TouristController {
    private final TouristService touristService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTourist(@RequestBody TouristDTO touristDTO) throws LoginAlreadyUsedException {
        touristService.saveTourist(touristDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TouristLoggedDTO> login(@RequestBody TouristLoggingDTO touristLoggingDTO) throws TouristNotFoundException, WrongPasswordException {
        return ResponseEntity.ok(touristService.login(touristLoggingDTO));
    }
    @Operation(summary = "Find tourist by login")
    @GetMapping()
    public ResponseEntity<TouristDTOGet> findByLogin(@RequestParam String login) throws TouristNotFoundException{
        return ResponseEntity.ok(touristService.findByLogin(login));
    }
    @Operation(summary = "Check if login is taken")
    @GetMapping("logincheck")
    public ResponseEntity<Boolean> existsByLogin(@RequestParam String login) {
        return ResponseEntity.ok(touristService.existsByLogin(login));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TouristDTOGet> modifyTourist(@RequestBody TouristDTO touristDTO) throws TouristNotFoundException {
        return ResponseEntity.ok(touristService.modifyTourist(touristDTO));
    }

    @Operation(summary = "Showing all tourists - for admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All tourists from database", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = "admin")
    public ResponseEntity<List<TouristDTOGet>> showAllTourists() {
        return ResponseEntity.ok(touristService.showAllTourists());
    }

    @DeleteMapping("{touristId}")
    public ResponseEntity<Void> deleteTourist(@PathVariable Long touristId) throws TouristNotFoundException {
        touristService.deleteTourist(touristId);
        return ResponseEntity.ok().build();
    }
}
