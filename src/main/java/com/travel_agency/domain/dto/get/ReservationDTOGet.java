package com.travel_agency.domain.dto.get;

import com.travel_agency.domain.Tourist;

import java.time.LocalDate;

public record ReservationDTOGet(Tourist reservationOwner, String hotelName, LocalDate checkIn_date,
                                LocalDate checkOut_date) {
}
