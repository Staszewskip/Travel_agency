package com.travel_agency.domain.dto.get;

import com.travel_agency.domain.Role;

import java.time.LocalDate;

public record TouristDTOGet(Long touristId, String firstname, String lastname,String login, LocalDate birthdate, String email, int phoneNumber,
                            Role role) {
}
