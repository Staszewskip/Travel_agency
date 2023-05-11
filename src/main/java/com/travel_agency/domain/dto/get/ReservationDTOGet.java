package com.travel_agency.domain.dto.get;

import com.travel_agency.domain.TouristGuest;
import com.travel_agency.domain.dto.TouristDTO;

import java.time.LocalDate;
import java.util.List;

public record ReservationDTOGet(Long reservationOwnerId, String hotelName, LocalDate checkIn_date,
                                LocalDate checkOut_date, List<TouristGuest> touristGuestList, Long totalPrice) {
}
