package com.travel_agency.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.travel_agency.config.AdminConfig;
import com.travel_agency.domain.Quote;
import com.travel_agency.domain.dto.weather.WeatherDTO;
import com.travel_agency.exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;

    public Response getForecast(String location) throws LocationNotFoundException, IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(adminConfig.getWeatherApiEndpoint() + location + "/summary/")
                .get()
                .addHeader("X-RapidAPI-Key", "58e256cde8msh201e19be7623aecp13f735jsnd8504b9a0583")
                .addHeader("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    public ResponseEntity<WeatherDTO> getForecast2(String location) throws LocationNotFoundException {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getWeatherApiEndpoint() + location + "/summary/")
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
//        headers.set("content-type", "application/octet-stream");
        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
        headers.set("X-RapidAPI-Host", adminConfig.getWeatherApiHost());

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<WeatherDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherDTO.class);
        return Optional.ofNullable(response)
                .orElseThrow(LocationNotFoundException::new);
    }
    public ResponseEntity<Quote> getQuote() {
        URI url = UriComponentsBuilder.fromHttpUrl("https://quotes15.p.rapidapi.com/quotes/random/")
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
        headers.set("X-RapidAPI-Host", "quotes15.p.rapidapi.com");

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Quote> response = restTemplate.exchange(url, HttpMethod.GET, entity, Quote.class);
        return response;
    }
}
