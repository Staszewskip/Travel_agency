package com.travel_agency.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("https://forecast9.p.rapidapi.com/rapidapi/forecast/")
    private String apiEndpoint;

    @Value("58e256cde8msh201e19be7623aecp13f735jsnd8504b9a0583")
    private String apiKey;

    @Value("forecast9.p.rapidapi.com")
    private String apiHost;
}