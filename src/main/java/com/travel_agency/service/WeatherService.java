package com.travel_agency.service;

import com.travel_agency.config.AdminConfig;
import com.travel_agency.domain.dto.weather.WeatherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;

    public ResponseEntity<WeatherDTO> getForecast(String location) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getWeatherApiEndpoint() + location + "/summary/")
                .build()
                .encode()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
        headers.set("X-RapidAPI-Host", adminConfig.getWeatherApiHost());

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<WeatherDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherDTO.class);
        return responseEntity;
    }
}
