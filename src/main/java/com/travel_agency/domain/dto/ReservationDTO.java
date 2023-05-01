package com.travel_agency.domain.dto;

import com.travel_agency.domain.AccomodationType;
import com.travel_agency.domain.Tourist;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReservationDTO {
    private Long reservationId;
    private Long reservationOwner;
    private LocalDate checkIn_date;
    private LocalDate checkOut_date;
    private AccomodationType accomodationType;

}
