package com.travel_agency.domain.dto;

import java.time.LocalDate;

public record ReservationDTO(Long reservationId, Long reservationOwnerId, Long hotelId, LocalDate checkIn_date,
                             LocalDate checkOut_date) {
}
