package com.travel_agency.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDTO {
    @JsonProperty("items")
    List<ItemsDTO> itemsDTOList;
}
