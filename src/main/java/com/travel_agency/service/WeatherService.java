package com.travel_agency.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.travel_agency.config.AdminConfig;
import com.travel_agency.domain.dto.WeatherDTO;
import com.travel_agency.exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;

    public Response getForecast(String location) throws LocationNotFoundException, IOException {
//        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getApiEndpoint() + location + "/summary/")
//                .build()
//                .encode()
//                .toUri();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-RapidAPI-Host", adminConfig.getApiHost());
//        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
//
//        HttpEntity entity = new HttpEntity(headers);
//
//        ResponseEntity<WeatherDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity,String.class );
//        return Optional.ofNullable(response)
//                .orElseThrow(LocationNotFoundException::new);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(adminConfig.getApiEndpoint() + location + "/summary/")
                .get()
                .addHeader("X-RapidAPI-Key", adminConfig.getApiHost())
                .addHeader("X-RapidAPI-Host", adminConfig.getApiKey())
                .build();
        Response response = client.newCall(request).execute();
       return response;
    }
}
