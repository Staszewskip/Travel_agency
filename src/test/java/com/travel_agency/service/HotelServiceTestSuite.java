package com.travel_agency.service;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.domain.dto.get.HotelDTOGet;
import com.travel_agency.exception.DestinationNotFoundException;
import com.travel_agency.exception.HotelNotFoundException;
import com.travel_agency.repository.DestinationRepository;
import com.travel_agency.repository.HotelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class HotelServiceTestSuite {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private HotelService hotelService;

    @AfterEach
    void cleanRepository() {
        hotelRepository.deleteAll();
        destinationRepository.deleteAll();
    }

    @Test
    void saveHotel() throws DestinationNotFoundException {
        // Given
        Destination destination = new Destination("country", "city", "postcode");
        Destination savedDestination = destinationRepository.save(destination);
        HotelDTO hotelDTO = new HotelDTO(1L, "Hotel_name", savedDestination.getDestinationId(), 100);
        // When
        hotelService.saveHotel(hotelDTO);
        // Then
        assertEquals(1, hotelRepository.count());
    }

    @Test
    void findHotels() throws DestinationNotFoundException {
        Destination destination = new Destination("country", "city", "postcode");
        Destination savedDestination = destinationRepository.save(destination);
        HotelDTO hotelDTO = new HotelDTO(1L, "Hotel_name", savedDestination.getDestinationId(), 100);
        HotelDTO hotelDTO2 = new HotelDTO(1L, "Hotel_name", savedDestination.getDestinationId(), 100);
        hotelService.saveHotel(hotelDTO);
        hotelService.saveHotel(hotelDTO2);
        // When
        List<HotelDTO> hotelDTOList = hotelService.showHotels();
        // Then
        assertEquals(2, hotelDTOList.size());
    }

    @Test
    void getHotels() throws DestinationNotFoundException {
        Destination destination = new Destination("country", "city", "postcode");
        Destination savedDestination = destinationRepository.save(destination);
        HotelDTO hotelDTO = new HotelDTO(1L, "Hotel_name", savedDestination.getDestinationId(), 100);
        HotelDTO hotelDTO2 = new HotelDTO(1L, "Hotel_name", savedDestination.getDestinationId(), 100);
        hotelService.saveHotel(hotelDTO);
        hotelService.saveHotel(hotelDTO2);
        // When
        List<HotelDTOGet> hotelDTOList = hotelService.getHotels();
        // Then
        assertEquals(2, hotelDTOList.size());
    }

    @Test
    void deleteHotel() throws HotelNotFoundException {
        Destination destination = new Destination("country", "city", "postcode");
        Destination savedDestination = destinationRepository.save(destination);
        Hotel hotel = new Hotel("Hotel_name", savedDestination, 200);
        Hotel hotel2 = new Hotel("Hotel_name", savedDestination, 200);
        Hotel savedHotel = hotelRepository.save(hotel);
        hotelRepository.save(hotel2);
        // When
        long size = hotelRepository.count();
        hotelService.deleteHotel(savedHotel.getHotelId());
        long updatedSize = hotelRepository.count();
        // Then
        assertNotEquals(size, updatedSize);
    }

    @Test
    void modifyHotel() throws HotelNotFoundException {
        Destination destination = new Destination("country", "city", "postcode");
        Destination savedDestination = destinationRepository.save(destination);
        Hotel hotel = new Hotel("Hotel_name", savedDestination, 100);
        Hotel savedHotel = hotelRepository.save(hotel);
        // When
        HotelDTO hotelDTO = new HotelDTO(savedHotel.getHotelId(), "Updated_name", savedDestination.getDestinationId(), 100);
        hotelService.modifyHotel(hotelDTO);
        Hotel updatedHotel = hotelRepository.save(hotel);
        String updatedName = updatedHotel.getName();
        // Then
        assertEquals(updatedName, hotel.getName());
    }
}