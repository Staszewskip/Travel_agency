package com.travel_agency.controller;

import com.travel_agency.exception.CurrencyNotFoundException;
import com.travel_agency.service.CurrencyService;
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

    @GetMapping
    public ResponseEntity<String> currencyConverter(@RequestParam String currentCurrencyCode, @RequestParam String desiredCurrencyCode, @RequestParam int amount) throws CurrencyNotFoundException {
        return currencyService.currencyConverter(currentCurrencyCode, desiredCurrencyCode, amount);
    }
}
