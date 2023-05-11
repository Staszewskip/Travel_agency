package com.travel_agency.service;

import com.travel_agency.exception.CurrencyNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceTestSuite {
    @Autowired
    private CurrencyService currencyService;

    @Test
    void currencyConverter() throws CurrencyNotFoundException {
        // Given & When
        ResponseEntity<String> stringResponseEntity = currencyService.currencyConverter("PLN", "EUR", 200);
        // Then
        assertTrue(stringResponseEntity.hasBody());
    }
}