package com.travel_agency.service;

import com.travel_agency.domain.dto.skyscanner.SkyScannerDTO;
import com.travel_agency.domain.dto.weather.WeatherDTO;
import com.travel_agency.exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkyScannerService {
    private final RestTemplate restTemplate;

    public List<SkyScannerDTO> getForecast2(String location) {
        URI url = UriComponentsBuilder.fromHttpUrl("https://partners.api.skyscanner.net/apiservices/browsequotes/v1.0/")
                .queryParam("country")
                .queryParam("currency")
                .queryParam("locale")
                .build()
                .encode()
                .toUri();

        SkyScannerDTO[] response = restTemplate.getForObject(url, SkyScannerDTO[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
