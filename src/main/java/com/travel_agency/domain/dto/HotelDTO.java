package com.travel_agency.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class HotelDTO {

    private Long hotelId;

    private String name;

    private Long destinationId;

    public HotelDTO(Long hotelId, String name) {
        this.hotelId = hotelId;
        this.name = name;
    }
}
