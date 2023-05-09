package com.travel_agency.domain.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {
    @JsonProperty("location")
    private LocationDTO location;
    @JsonProperty("forecast")
    private ForecastDTO forecast;
}
