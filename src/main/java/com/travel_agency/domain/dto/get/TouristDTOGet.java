package com.travel_agency.domain.dto.get;

public record TouristDTOGet(Long touristId, String firstname, String lastname, boolean isAdult, String email, int phoneNumber) {
}
