package com.travel_agency.domain.dto.get;

import java.time.LocalDate;

public record TouristDTOGet(Long touristId, String firstname, String lastname, LocalDate birthdate, String email, int phoneNumber) {
}
