package com.travel_agency.service;

import com.travel_agency.config.AdminConfig;
import com.travel_agency.logger.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;
    private Logger logger = Logger.INSTANCE;

    public ResponseEntity<String> getForecast(String location)  {

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getWeatherApiEndpoint() + location + "/summary/")
                .build()
                .encode()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
        headers.set("X-RapidAPI-Host", adminConfig.getWeatherApiHost());

        HttpEntity entity = new HttpEntity(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
//    public ResponseEntity<WeatherDTO> getForecast2(String location)  {
//
//        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getWeatherApiEndpoint() + location + "/summary/")
//                .build()
//                .encode()
//                .toUri();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
//        headers.set("X-RapidAPI-Host", adminConfig.getWeatherApiHost());
//
//        HttpEntity entity = new HttpEntity(headers);
//        ResponseEntity<WeatherDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherDTO.class);
//          return response;
//    }


}
