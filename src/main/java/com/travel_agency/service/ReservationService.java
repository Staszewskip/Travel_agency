package com.travel_agency.service;

import com.travel_agency.domain.Reservation;
import com.travel_agency.domain.dto.ReservationDTO;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.domain.dto.TouristGuestDTO;
import com.travel_agency.exception.ReservationNotFoundException;
import com.travel_agency.mapper.ReservationMapper;
import com.travel_agency.mapper.TouristGuestMapper;
import com.travel_agency.mapper.TouristMapper;
import com.travel_agency.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;
    private final TouristMapper touristMapper;
    private final TouristGuestMapper touristGuestMapper;
    private final ReservationRepository reservationRepository;

    public void saveReservation(final ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.mapToReservation(reservationDTO);
        reservationRepository.save(reservation);
    }
    public void addTouristsReservation(Long reservationId, TouristGuestDTO touristGuestDTO) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        reservation.getTouristGuestsList().add(touristGuestMapper.mapToTouristGuest(touristGuestDTO));
        reservationRepository.save(reservation);
    }
    public List<ReservationDTO> showReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationMapper.mapToReservationDTOList(reservationList);
    }
    public void deleteReservation(Long reservationId) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.delete(reservation);
    }
}
