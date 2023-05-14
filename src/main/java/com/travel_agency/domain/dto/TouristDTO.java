package com.travel_agency.domain.dto;

import java.time.LocalDate;

public record TouristDTO(Long touristId, String firstname, String lastname, LocalDate birthdate, String login,
                         String password, String email, int phoneNumber) {
}
