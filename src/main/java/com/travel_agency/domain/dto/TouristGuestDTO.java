package com.travel_agency.domain.dto;

import java.time.LocalDate;

public record TouristGuestDTO(String firstname, String lastname, LocalDate birthdate) {
}
