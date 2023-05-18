package com.travel_agency.domain.dto;

import com.travel_agency.domain.Role;

import java.time.LocalDate;

public record TouristDTO(Long touristId, String firstname, String lastname, LocalDate birthdate, String login,
                         String password, String email, int phoneNumber, Role role) {
}
