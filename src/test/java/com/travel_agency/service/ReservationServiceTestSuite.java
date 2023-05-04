package com.travel_agency.service;

import com.travel_agency.domain.*;
import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.domain.dto.ReservationDTO;
import com.travel_agency.domain.dto.get.ReservationDTOGet;
import com.travel_agency.exception.HotelNotFoundException;
import com.travel_agency.exception.ReservationNotFoundException;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.repository.DestinationRepository;
import com.travel_agency.repository.HotelRepository;
import com.travel_agency.repository.ReservationRepository;
import com.travel_agency.repository.TouristRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceTestSuite {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private TouristRepository touristRepository;
    @Autowired
    private HotelRepository hotelRepository;


    @Test
    void saveReservation() throws TouristNotFoundException, HotelNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", true, "login", "password", "email", 123456);
        Tourist savedTourist = touristRepository.save(tourist);
        Destination destination = new Destination("country", "city", "postcode");
        Destination savedDestination = destinationRepository.save(destination);
        Hotel hotel = new Hotel("Hotel_name", savedDestination);
        Hotel savedHotel = hotelRepository.save(hotel);
        savedDestination.getHotelList().add(hotel);
        ReservationDTO reservationDTO = new ReservationDTO(1L, savedTourist.getTouristId(), savedHotel.getHotelId(), LocalDate.now(), LocalDate.now().plusDays(10), AccomodationType.INSTANCE);
        // When
        reservationService.saveReservation(reservationDTO);
        // Then
        assertEquals(1, reservationRepository.count());
        // Cleanup
        reservationRepository.deleteAll();
        hotelRepository.deleteAll();
        destinationRepository.deleteAll();
        touristRepository.deleteAll();
    }

    @Test
    void addTouristsReservation() {

    }

    @Test
    void getReservationsOfGivenUser() throws TouristNotFoundException, HotelNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", true, "login", "password", "email", 123456);
        Tourist savedTourist = touristRepository.save(tourist);
        Destination destination = new Destination("country", "city", "postcode");
        Destination savedDestination = destinationRepository.save(destination);
        Hotel hotel = new Hotel("Hotel_name", savedDestination);
        Hotel savedHotel = hotelRepository.save(hotel);
        savedDestination.getHotelList().add(hotel);
        ReservationDTO reservationDTO = new ReservationDTO(1L, savedTourist.getTouristId(), savedHotel.getHotelId(), LocalDate.now(), LocalDate.now().plusDays(10), AccomodationType.INSTANCE);
        reservationService.saveReservation(reservationDTO);
        // When
        List<ReservationDTOGet> foundList = reservationService.getReservationsOfGivenUser("tourist", "lastname");
        // Then
        assertEquals(1, foundList.size());
        // Cleanup
        reservationRepository.deleteAll();
        hotelRepository.deleteAll();
        destinationRepository.deleteAll();
        touristRepository.deleteAll();
    }

    @Test
    void showReservations() throws TouristNotFoundException, HotelNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", true, "login", "password", "email", 123456);
        Tourist savedTourist = touristRepository.save(tourist);
        Destination destination = new Destination("country", "city", "postcode");
        Destination savedDestination = destinationRepository.save(destination);
        Hotel hotel = new Hotel("Hotel_name", savedDestination);
        Hotel savedHotel = hotelRepository.save(hotel);
        savedDestination.getHotelList().add(hotel);
        ReservationDTO reservationDTO = new ReservationDTO(1L, savedTourist.getTouristId(), savedHotel.getHotelId(), LocalDate.now(), LocalDate.now().plusDays(10), AccomodationType.INSTANCE);
        reservationService.saveReservation(reservationDTO);
        // When
        List<ReservationDTO> reservationDTOList = reservationService.showReservations();
        // Then
        assertEquals(1, reservationDTOList.size());
        // Cleanup
        reservationRepository.deleteAll();
        hotelRepository.deleteAll();
        destinationRepository.deleteAll();
        touristRepository.deleteAll();
    }

    @Test
    void deleteReservation() throws TouristNotFoundException, HotelNotFoundException, ReservationNotFoundException {
        // Given
//        Tourist tourist = new Tourist("tourist", "lastname", true, "login", "password", "email", 123456);
//        Tourist savedTourist = touristRepository.save(tourist);
//        Destination destination = new Destination("country", "city", "postcode");
//        Destination savedDestination = destinationRepository.save(destination);
//        Hotel hotel = new Hotel("Hotel_name", savedDestination);
//        Hotel savedHotel = hotelRepository.save(hotel);
//        savedDestination.getHotelList().add(hotel);
//        ReservationDTO reservationDTO = new ReservationDTO(1L, savedTourist.getTouristId(), savedHotel.getHotelId(), LocalDate.now(), LocalDate.now().plusDays(10), AccomodationType.INSTANCE);
//        reservationService.saveReservation(reservationDTO);
//        // When
//        reservationService.deleteReservation(reservationDTO.reservationId());
//        // Then
//        assertNotEquals(1,reservationRepository.count());
//        // Cleanup
//        reservationRepository.deleteAll();
//        hotelRepository.deleteAll();
//        destinationRepository.deleteAll();
//        touristRepository.deleteAll();
    }

    @Test
    void modifyReservation() {
    }
}