package com.travel_agency.mapper;

import com.travel_agency.domain.Reservation;
import com.travel_agency.domain.dto.get.ReservationDTOGet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationMapper {
    public ReservationDTOGet mapToReservationDTOGet(Reservation reservation) {
        return new ReservationDTOGet(
                reservation.getReservationOwner().getTouristId(),
                reservation.getHotel().getName(),
                reservation.getCheckIn_date(),
                reservation.getCheckOut_date(),
                reservation.getTouristGuestsList(),
                reservation.getTotalPrice()
        );
    }

    public List<ReservationDTOGet> mapToReservationDTOGetList(List<Reservation> reservationList) {
        return reservationList.stream()
                .map(this::mapToReservationDTOGet)
                .collect(Collectors.toList());
    }
}
