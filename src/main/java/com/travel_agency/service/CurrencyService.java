package com.travel_agency.service;

import com.travel_agency.config.AdminConfig;
import com.travel_agency.domain.dto.CurrencyDTO;
import com.travel_agency.domain.dto.weather.WeatherDTO;
import com.travel_agency.exception.CurrencyNotFoundException;
import com.travel_agency.exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;

    public  ResponseEntity<String> currencyConverter(String currentCurrencyCode, String desiredCurrencyCode, int amount) throws CurrencyNotFoundException {

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getCurrencyApiEndpoint())
                .queryParam("have", currentCurrencyCode)
                .queryParam("want", desiredCurrencyCode)
                .queryParam("amount", amount)
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
        headers.set("X-RapidAPI-Host", adminConfig.getCurrencyApiHost());

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return Optional.ofNullable(response)
                .orElseThrow(CurrencyNotFoundException::new);
    }
}
