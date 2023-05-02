package com.travel_agency.mapper;

import com.travel_agency.domain.Reservation;
import com.travel_agency.domain.dto.ReservationDTO;
import com.travel_agency.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationMapper {
    private final TouristRepository touristRepository;

    public ReservationDTO mapToReservationDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getReservationId(),
                reservation.getReservationOwner().getTouristId(),
                reservation.getCheckIn_date(),
                reservation.getCheckOut_date(),
                reservation.getAccomodationType()
        );
    }

    public Reservation mapToReservation(ReservationDTO reservationDTO) {
        return new Reservation(
                touristRepository.findById(reservationDTO.reservationOwner()).get(),
                reservationDTO.checkIn_date(),
                reservationDTO.checkOut_date(),
                reservationDTO.accomodationType()
        );
    }

    public List<ReservationDTO> mapToReservationDTOList(List<Reservation> reservationList) {
        return reservationList.stream()
                .map(this::mapToReservationDTO)
                .collect(Collectors.toList());
    }
}
