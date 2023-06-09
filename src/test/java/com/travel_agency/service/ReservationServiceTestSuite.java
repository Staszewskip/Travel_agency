package com.travel_agency.service;

import com.travel_agency.domain.*;
import com.travel_agency.domain.dto.ReservationDTO;
import com.travel_agency.domain.dto.TouristGuestDTO;
import com.travel_agency.domain.dto.get.ReservationDTOGet;
import com.travel_agency.exception.HotelNotFoundException;
import com.travel_agency.exception.ReservationNotFoundException;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
    private TouristGuestRepository touristGuestRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @AfterEach
    void cleanRepository() {
        reservationRepository.deleteAll();
        hotelRepository.deleteAll();
        destinationRepository.deleteAll();
        touristRepository.deleteAll();
        touristGuestRepository.deleteAll();
    }


    @Test
    void saveReservation() throws TouristNotFoundException, HotelNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password","passwordHash", "email", 123456, Role.USER);
        Tourist savedTourist = touristRepository.save(tourist);

        Destination destination = new Destination("country", "city");
        Destination savedDestination = destinationRepository.save(destination);

        Hotel hotel = new Hotel("Hotel_name", savedDestination, 100);
        Hotel savedHotel = hotelRepository.save(hotel);

        savedDestination.getHotelList().add(hotel);
        hotelRepository.save(hotel);
        ReservationDTO reservationDTO = new ReservationDTO(1L, savedTourist.getTouristId(), savedHotel.getHotelId(), LocalDate.now(), LocalDate.now().plusDays(10));        // When
        reservationService.saveReservation(reservationDTO);
        // Then
        assertEquals(1, reservationRepository.count());
    }

    @Test
    void addTouristsReservation() throws ReservationNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password","passwordHash",  "email", 123456,Role.USER);
        touristRepository.save(tourist);

        Destination destination = new Destination("country", "city");
        Destination savedDestination = destinationRepository.save(destination);

        Hotel hotel = new Hotel("Hotel_name", savedDestination, 100);

        savedDestination.getHotelList().add(hotel);
        hotelRepository.save(hotel);
        Reservation reservation = new Reservation(tourist, hotel, LocalDate.now(), LocalDate.now().plusDays(10));
        Reservation savedReservation = reservationRepository.save(reservation);
        TouristGuestDTO touristGuestDTO = new TouristGuestDTO("firstname", "lastname", LocalDate.now());
        // When
        Reservation modifiedReservation = reservationService.addTouristsReservation(savedReservation.getReservationId(), touristGuestDTO);
        // Then
        assertEquals(1, modifiedReservation.getTouristGuestsList().size());
    }

    @Test
    void getReservationsOfGivenUser() throws TouristNotFoundException, HotelNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password","passwordHash",  "email", 123456,Role.USER);
        Tourist savedTourist = touristRepository.save(tourist);
        Destination destination = new Destination("country", "city");
        Destination savedDestination = destinationRepository.save(destination);
        Hotel hotel = new Hotel("Hotel_name", savedDestination, 100);
        Hotel savedHotel = hotelRepository.save(hotel);
        savedDestination.getHotelList().add(hotel);
        ReservationDTO reservationDTO = new ReservationDTO(1L, savedTourist.getTouristId(), savedHotel.getHotelId(), LocalDate.now(), LocalDate.now().plusDays(10));
        reservationService.saveReservation(reservationDTO);
        // When
        List<ReservationDTOGet> foundList = reservationService.getReservationsOfGivenUser("tourist", "lastname");
        // Then
        assertEquals(1, foundList.size());
    }

    @Test
    void showReservations() throws TouristNotFoundException, HotelNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password","passwordHash",  "email", 123456,Role.USER);
        Tourist savedTourist = touristRepository.save(tourist);
        Destination destination = new Destination("country", "city");
        Destination savedDestination = destinationRepository.save(destination);
        Hotel hotel = new Hotel("Hotel_name", savedDestination, 100);
        Hotel savedHotel = hotelRepository.save(hotel);
        savedDestination.getHotelList().add(hotel);
        ReservationDTO reservationDTO = new ReservationDTO(1L, savedTourist.getTouristId(), savedHotel.getHotelId(), LocalDate.now(), LocalDate.now().plusDays(10));
        reservationService.saveReservation(reservationDTO);
        // When
        List<ReservationDTOGet> reservationDTOList = reservationService.showReservations();
        // Then
        assertEquals(1, reservationDTOList.size());
    }

    @Test
    void deleteReservation() throws TouristNotFoundException, HotelNotFoundException, ReservationNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password","passwordHash",  "email", 123456,Role.USER);
        Tourist savedTourist = touristRepository.save(tourist);
        Destination destination = new Destination("country", "city");
        Destination savedDestination = destinationRepository.save(destination);
        Hotel hotel = new Hotel("Hotel_name", savedDestination, 100);
        Hotel savedHotel = hotelRepository.save(hotel);
        savedDestination.getHotelList().add(hotel);
        ReservationDTO reservationDTO = new ReservationDTO(1L, savedTourist.getTouristId(), savedHotel.getHotelId(), LocalDate.now(), LocalDate.now().plusDays(10));
        Reservation savedReservation = reservationService.saveReservation(reservationDTO);
        // When
        reservationService.deleteReservation(savedReservation.getReservationId());
        // Then
        assertNotEquals(1, reservationRepository.count());
    }

    @Test
    void modifyReservation() throws ReservationNotFoundException {
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password","passwordHash",  "email", 123456,Role.USER);
        touristRepository.save(tourist);

        Destination destination = new Destination("country", "city");
        Destination savedDestination = destinationRepository.save(destination);

        Hotel hotel = new Hotel("Hotel_name", savedDestination, 100);
        hotelRepository.save(hotel);

        savedDestination.getHotelList().add(hotel);
        hotelRepository.save(hotel);
        Reservation reservation = new Reservation(tourist, hotel, LocalDate.now(), LocalDate.now().plusDays(10));
        Reservation savedReservation = reservationRepository.save(reservation);
        // When
        ReservationDTOGet modifiedReservation = reservationService.modifyReservation(savedReservation.getReservationId(), LocalDate.of(2023, 1, 2), LocalDate.now());
        // Then
        assertEquals(LocalDate.now(), modifiedReservation.checkOut_date());
    }
}