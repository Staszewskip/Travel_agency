package com.travel_agency.domain.dto;

public record TouristDTO(Long touristId, String firstname, String lastname, boolean isAdult, String login,
                         String password, String email, int phoneNumber) {
}
