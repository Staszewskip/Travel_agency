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

    public  ResponseEntity<CurrencyDTO> currencyConverter(String basicCurrencyCode, String desiredCurrencyCode, int amount) throws CurrencyNotFoundException {

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getCurrencyApiEndpoint())
                .queryParam("format", "json")
                .queryParam("from", basicCurrencyCode)
                .queryParam("to", desiredCurrencyCode)
                .queryParam("amount", amount)
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
        headers.set("X-RapidAPI-Host", adminConfig.getWeatherApiHost());

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<CurrencyDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, CurrencyDTO.class);
        return Optional.ofNullable(response)
                .orElseThrow(CurrencyNotFoundException::new);
    }
}
