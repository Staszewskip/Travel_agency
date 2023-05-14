package com.travel_agency.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${rapidapi.key}")
    private String apiKey;

    @Value("https://forecast9.p.rapidapi.com/rapidapi/forecast/")
    private String weatherApiEndpoint;

    @Value("https://currency-converter-by-api-ninjas.p.rapidapi.com/v1/convertcurrency")
    private String currencyApiEndpoint;

    @Value("forecast9.p.rapidapi.com")
    private String weatherApiHost;

    @Value("currency-converter-by-api-ninjas.p.rapidapi.com")
    private String currencyApiHost;

    @Value("p.staszewski1@kodilla.com")
    private String adminMail;
}
