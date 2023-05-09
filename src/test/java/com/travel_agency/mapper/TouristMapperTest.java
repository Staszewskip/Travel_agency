package com.travel_agency.mapper;

import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.domain.dto.get.TouristDTOGet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TouristMapperTest {
TouristMapper touristMapper = new TouristMapper();

    @Test
    void mapToTourist() {
        // Given
        TouristDTO touristDTO = new TouristDTO(0L, "tourist", "lastname", true, "login", "password", "email", 123456);
        Tourist tourist = new Tourist("tourist", "lastname", true, "login", "password", "email", 123456);
        // When
        Tourist mappedTourist = touristMapper.mapToTourist(touristDTO);
        TouristDTO mappedTouristDTO = touristMapper.mapToTouristDTO(tourist);
        // Then
        Assertions.assertAll(
                () ->assertEquals(tourist,mappedTourist),
                () ->assertEquals(touristDTO,mappedTouristDTO)
        );
    }
        @Test
    void mapToTouristDTOList() {
    }

    @Test
    void mapToTouristDTOget() {
    }

    @Test
    void mapToTouristDTOGetList() {
    }
}