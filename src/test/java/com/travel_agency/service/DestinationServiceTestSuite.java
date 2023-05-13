package com.travel_agency.service;

import com.jayway.jsonpath.Criteria;
import com.mysql.cj.Session;
import com.travel_agency.domain.Destination;
import com.travel_agency.domain.dto.DestinationDTO;
import com.travel_agency.domain.dto.get.DestinationDTOGet;
import com.travel_agency.exception.DestinationNotFoundException;
import com.travel_agency.repository.DestinationRepository;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class DestinationServiceTestSuite {
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private DestinationService destinationService;

    @AfterEach
    void cleanRepository() {
        destinationRepository.deleteAll();
    }

    @Test
    void saveDestination() {
        // Given
        DestinationDTO destinationDTO = new DestinationDTO(1L, "country", "city", "postcode");
        // When
        destinationService.saveDestination(destinationDTO);
        // Then
        assertEquals(1, destinationRepository.count());
    }

    @Test
    void showDestinations() {
        // Given
        DestinationDTO destinationDTO = new DestinationDTO(1L, "country", "city", "postcode");
        DestinationDTO destinationDTO2 = new DestinationDTO(1L, "country", "city", "postcode");
        destinationService.saveDestination(destinationDTO);
        destinationService.saveDestination(destinationDTO2);
        // When
        List<DestinationDTO> foundDestinationDTOList = destinationService.showDestinations();
        // Then
        assertEquals(2, foundDestinationDTOList.size());
    }

    @Test
    void getDestinations() {
        // Given
        Destination destination = new Destination( "country", "city", "postcode");
        Destination destination2 = new Destination( "country", "city", "postcode");
        Hibernate.initialize(destination.getHotelList());
        Hibernate.initialize(destination2.getHotelList());
        destinationRepository.save(destination);
        destinationRepository.save(destination2);
        // When
        List<DestinationDTOGet> foundDestinationDTOList = destinationService.getDestinations();
        // Then
        assertEquals(2, foundDestinationDTOList.size());
    }

    @Test
    void deleteDestination() throws DestinationNotFoundException {
        // Given
        Destination destination = new Destination("country", "city", "postcode");
        Destination destination2 = new Destination("country", "city", "postcode");
        destinationRepository.save(destination);
        destinationRepository.save(destination2);
        // When
        long size = destinationRepository.count();
        destinationService.deleteDestination(destination.getDestinationId());
        long updatedSize = destinationRepository.count();
        // Then
        assertNotEquals(size, updatedSize);
    }

    @Test
    void modifyDestination() throws DestinationNotFoundException {
        // Given
        Destination destination = new Destination("country", "city", "postcode");
        destinationRepository.save(destination);
        long destinationId = destination.getDestinationId();
        // When
        DestinationDTO destinationDTO = new DestinationDTO(destinationId, "updated_country", "city", "postcode");
        destinationService.modifyDestination(destinationDTO);
        Destination updatedDestination = destinationRepository.save(destination);
        String updatedCountry = destination.getCountry();
        // Then
        assertEquals(updatedCountry, updatedDestination.getCountry());
    }
}