
package com.travel_agency.domain.dto.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "date",
    "dateWithTimezone",
    "weather",
    "sunHours",
    "rainHours",
    "temperature",
    "wind"
})
@Generated("jsonschema2pojo")
public class Item {
    @JsonProperty("date")
    private String date;
    @JsonProperty("sunHours")
    private Double sunHours;
    @JsonProperty("rainHours")
    private Double rainHours;
    @JsonProperty("temperature")
    @Valid
    private Temperature temperature;
    @JsonProperty("wind")
    @Valid
    private Wind wind;
}
