package com.travel_agency.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.travel_agency.config.AdminConfig;
import com.travel_agency.domain.dto.quote.QuoteDTO;
import com.travel_agency.domain.dto.weather.WeatherDTO;
import com.travel_agency.logger.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;
    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = Logger.INSTANCE;

    public WeatherDTO getForecast(String location) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://forecast9.p.rapidapi.com/rapidapi/forecast/" + location + "/summary/"))
                .header("X-RapidAPI-Key", "58e256cde8msh201e19be7623aecp13f735jsnd8504b9a0583")
                .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        WeatherDTO response = gson.fromJson(httpResponse.body(), WeatherDTO.class);
//        logger.log("");
        return response;
    }

    public ResponseEntity<WeatherDTO> getForecast2(String location)  {

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getWeatherApiEndpoint() + location + "/summary/")
                .build()
                .encode()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
        headers.set("X-RapidAPI-Host", adminConfig.getWeatherApiHost());

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<WeatherDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, WeatherDTO.class);
          return response;
    }

    public ResponseEntity<QuoteDTO> getQuote() {
        URI url = UriComponentsBuilder.fromHttpUrl("https://quotes15.p.rapidapi.com/quotes/random/")
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", adminConfig.getApiKey());
        headers.set("X-RapidAPI-Host", "quotes15.p.rapidapi.com");

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<QuoteDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, QuoteDTO.class);
        return response;
    }

    public String getQuote2() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://quotes15.p.rapidapi.com/quotes/random/"))
                .header("X-RapidAPI-Key", "58e256cde8msh201e19be7623aecp13f735jsnd8504b9a0583")
                .header("X-RapidAPI-Host", "quotes15.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
