package com.travel_agency.controller;

import com.travel_agency.domain.dto.CurrencyDTO;
import com.travel_agency.exception.CurrencyNotFoundException;
import com.travel_agency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/converter")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<CurrencyDTO> currencyConverter(@RequestParam String basicCurrencyCode,@RequestParam String desiredCurrencyCode,@RequestParam int amount) throws CurrencyNotFoundException {
        return currencyService.currencyConverter(basicCurrencyCode, desiredCurrencyCode, amount);
    }
}
