package com.travel_agency.domain.dto.get;

import com.travel_agency.domain.dto.ReservationDTO;

import java.time.LocalDate;
import java.util.List;

public record TouristGuestDTOGet(String firstname, String lastname, LocalDate birthdate, List<ReservationDTO> reservationDTOList) {
}
