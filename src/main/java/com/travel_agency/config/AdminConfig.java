package com.travel_agency.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("https://forecast9.p.rapidapi.com/rapidapi/forecast/")
    private String weatherApiEndpoint;

    @Value("https://currency-converter5.p.rapidapi.com/currency/convert")
    private String currencyApiEndpoint;

    @Value("58e256cde8msh201e19be7623aecp13f735jsnd8504b9a0583")
    private String apiKey;

    @Value("forecast9.p.rapidapi.com")
    private String weatherApiHost;

    @Value("currency-converter5.p.rapidapi.com")
    private String CurrencyApiHost;
}
