package com.travel_agency.service;

import com.travel_agency.domain.Role;
import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.exception.LoginAlreadyUsedException;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.repository.TouristRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TouristServiceTestSuite {
    @Autowired
    private TouristRepository touristRepository;
    @Autowired
    private TouristService touristService;

    @AfterEach
    void cleanRepository() {
        touristRepository.deleteAll();
    }

    @Test
    void saveTourist() throws LoginAlreadyUsedException {
        // Given
        TouristDTO touristDTO = new TouristDTO(1L, "tourist", "lastname", LocalDate.now(), "login", "password", "email", 123456, Role.USER);
        // When
        touristService.saveTourist(touristDTO);
        // Then
        assertEquals(1, touristRepository.count());
    }

    @Test
    void findAllTourists() {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password", "passwordHash", "email", 123456, Role.USER);
        Tourist tourist2 = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password", "passwordHash", "email", 123456, Role.USER);
        // When
        touristRepository.save(tourist);
        touristRepository.save(tourist2);
        long size = touristService.showAllTourists().size();
        // Then
        assertEquals(2, size);
    }

    @Test
    void findByLogin() throws TouristNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password", "passwordHash", "email", 123456, Role.USER);
        // When
        Tourist savedTourist = touristRepository.save(tourist);
        String foundLogin = touristService.findByLogin(savedTourist.getLogin()).login();
        // Then
        assertEquals("login", foundLogin);
    }

    @Test
    void deleteTourist() throws TouristNotFoundException {
        // Given
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password", "passwordHash", "email", 123456, Role.USER);
        Tourist tourist2 = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password", "passwordHash", "email", 123456, Role.USER);
        // When
        touristRepository.save(tourist);
        touristRepository.save(tourist2);
        long size = touristRepository.count();
        touristService.deleteTourist(tourist2.getTouristId());
        long updatedSize = touristRepository.count();
        // Then
        assertNotEquals(size, updatedSize);
    }

    @Test
    void modifyTourist() throws TouristNotFoundException {
        Tourist tourist = new Tourist("tourist", "lastname", LocalDate.now(), "login", "password", "passwordHash", "email", 123456, Role.USER);
        touristRepository.save(tourist);
        long touristId = tourist.getTouristId();
        // When
        TouristDTO touristDTO = new TouristDTO(touristId, "updated_tourist", "lastname", LocalDate.now(), "login", "password", "email", 123456, Role.USER);
        touristService.modifyTourist(touristDTO);
        Tourist updatedTourist = touristRepository.save(tourist);
        String updatedName = tourist.getFirstname();
        // Then
        assertEquals(updatedName, updatedTourist.getFirstname());
    }
}