
package com.travel_agency.domain.dto.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "unit",
    "direction",
    "avg",
    "min",
    "max"
})
@Generated("jsonschema2pojo")
public class Wind {

    @JsonProperty("unit")
    private String unit;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("avg")
    private Integer avg;
    @JsonProperty("min")
    private Integer min;
    @JsonProperty("max")
    private Integer max;
}
