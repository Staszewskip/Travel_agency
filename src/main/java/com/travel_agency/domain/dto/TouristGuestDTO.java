package com.travel_agency.domain.dto;

import java.util.List;

public record TouristGuestDTO(String firstname, String lastname, boolean isAdult, List<ReservationDTO> reservationDTOList) {
}
