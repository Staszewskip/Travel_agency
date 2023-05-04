package com.travel_agency.domain.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {
    @JsonProperty("name")
    private String name;

//    @JsonProperty("forecast")
//    private List<ForecastDTO> forecast;
}
