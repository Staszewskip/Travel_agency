package com.travel_agency.domain.dto;

import com.travel_agency.domain.AccomodationType;

import java.time.LocalDate;

public record ReservationDTO(Long reservationId, Long reservationOwner, Long hotelId, LocalDate checkIn_date,
                             LocalDate checkOut_date, AccomodationType accomodationType) {
}
