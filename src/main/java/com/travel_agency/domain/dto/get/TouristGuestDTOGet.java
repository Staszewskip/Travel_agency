package com.travel_agency.domain.dto.get;

import com.travel_agency.domain.dto.ReservationDTO;

import java.util.List;

public record TouristGuestDTOGet(String firstname, String lastname, boolean isAdult, List<ReservationDTO> reservationDTOList) {
}
