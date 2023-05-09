package com.travel_agency.domain.dto;

import java.math.BigDecimal;

public record HotelDTO(Long hotelId, String name, Long destinationID, long unitPrice) {
}
