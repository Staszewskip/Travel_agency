package com.travel_agency.service;

import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.Reservation;
import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.TouristGuest;
import com.travel_agency.domain.dto.ReservationDTO;
import com.travel_agency.domain.dto.TouristGuestDTO;
import com.travel_agency.domain.dto.get.ReservationDTOGet;
import com.travel_agency.exception.HotelNotFoundException;
import com.travel_agency.exception.ReservationNotFoundException;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.mapper.ReservationMapper;
import com.travel_agency.mapper.TouristGuestMapper;
import com.travel_agency.repository.HotelRepository;
import com.travel_agency.repository.ReservationRepository;
import com.travel_agency.repository.TouristGuestRepository;
import com.travel_agency.repository.TouristRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;
    private final TouristGuestMapper touristGuestMapper;
    private final ReservationRepository reservationRepository;
    private final TouristRepository touristRepository;
    private final TouristGuestRepository touristGuestRepository;
    private final HotelRepository hotelRepository;

    public void saveReservation(final ReservationDTO reservationDTO) throws TouristNotFoundException, HotelNotFoundException {
        Tourist tourist = touristRepository.findById(reservationDTO.reservationOwner()).orElseThrow(TouristNotFoundException::new);
        Hotel hotel = hotelRepository.findById(reservationDTO.hotelId()).orElseThrow(HotelNotFoundException::new);
        Reservation reservation = new Reservation(tourist, hotel,reservationDTO.checkIn_date(), reservationDTO.checkOut_date());
        reservationRepository.save(reservation);
    }

    public void addTouristsReservation(Long reservationId, TouristGuestDTO touristGuestDTO) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        TouristGuest savedTouristGuest = touristGuestRepository.save(touristGuestMapper.mapToTouristGuest(touristGuestDTO));
        reservation.getTouristGuestsList().add(savedTouristGuest);
//        reservation.setTotalPrice(reservation.getTotalPrice());
        reservationRepository.save(reservation);
    }
    public List<ReservationDTOGet> getReservationsOfGivenUser(String firstname, String lastname) {
        List<Reservation> reservationList = reservationRepository.findByUser(firstname,lastname);
        return reservationMapper.mapToReservationDTOGetList(reservationList);
    }

    public List<ReservationDTO> showReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationMapper.mapToReservationDTOList(reservationList);
    }

    public void deleteReservation(Long reservationId) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.delete(reservation);
    }

    public ReservationDTO modifyReservation(Long reservationId, LocalDate checkIn, LocalDate checkOut) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        reservation.setCheckIn_date(checkIn);
        reservation.setCheckOut_date(checkOut);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return reservationMapper.mapToReservationDTO(updatedReservation);
    }
}
