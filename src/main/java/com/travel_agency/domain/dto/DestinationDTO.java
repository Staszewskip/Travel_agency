package com.travel_agency.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DestinationDTO {

    private Long destinationId;

    private String country;

    private String city;

    private String postcode;
}
