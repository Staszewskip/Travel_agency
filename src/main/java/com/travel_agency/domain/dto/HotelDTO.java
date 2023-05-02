package com.travel_agency.domain.dto;

import com.travel_agency.domain.Destination;

public record HotelDTO(Long hotelId, String name, Destination destination) {
}
