
package com.travel_agency.domain.dto.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "items",
    "forecastDate",
    "nextUpdate"
})
@Generated("jsonschema2pojo")
public class Forecast {

    @JsonProperty("items")
    @Valid
    private List<Item> items;
    @JsonProperty("forecastDate")
    private String forecastDate;
    @JsonProperty("nextUpdate")
    private String nextUpdate;
}
