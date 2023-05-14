package com.travel_agency.controller;

import com.travel_agency.exception.CurrencyNotFoundException;
import com.travel_agency.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/converter")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @Operation(summary = "Currency converter, API key required")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping
    public ResponseEntity<String> currencyConverter(@RequestParam String currentCurrencyCode, @RequestParam String desiredCurrencyCode, @RequestParam int amount) throws CurrencyNotFoundException {
        return currencyService.currencyConverter(currentCurrencyCode, desiredCurrencyCode, amount);
    }
}
