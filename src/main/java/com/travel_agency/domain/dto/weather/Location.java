
package com.travel_agency.domain.dto.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "coordinates"
})
@Generated("jsonschema2pojo")
public class Location {

    @JsonProperty("name")
    private String name;
    @JsonProperty("coordinates")
    @Valid
    private Coordinates coordinates;

}
