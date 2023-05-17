package com.travel_agency.mapper;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.DestinationDTO;
import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.domain.dto.get.DestinationDTOGet;
import com.travel_agency.domain.dto.get.HotelDTOGet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DestinationMapperTestSuite {
    DestinationMapper destinationMapper = new DestinationMapper();

    @Test
    void mapToDestination() {
        // Given
        Destination destination = new Destination("country", "city");
        DestinationDTO destinationDTO = new DestinationDTO(null, "country", "city");
        // When
        Destination mappedDestination = destinationMapper.mapToDestination(destinationDTO);
        // Then
        Assertions.assertEquals(destination, mappedDestination);
    }

    @Test
    void mapToDestinationDTO() {
        // Given
        Destination destination = new Destination("country", "city");
        DestinationDTO destinationDTO = new DestinationDTO(null, "country", "city");
        // When
        DestinationDTO mappedDestinationDTO = destinationMapper.mapToDestinationDTO(destination);
        // Then
        Assertions.assertEquals(destinationDTO, mappedDestinationDTO);
    }
    @Test
    void mapToDestinationDTOList() {
        List<Destination> destinationList = Arrays.asList(new Destination("country", "city"));
        List<DestinationDTO> destinationDTOList = Arrays.asList(new DestinationDTO(null, "country", "city"));
        // When
        List<DestinationDTO> mappedDestinationDTOList = destinationMapper.mapToDestinationDTOList(destinationList);
        // Then
        Assertions.assertEquals(destinationDTOList, mappedDestinationDTOList);
    }

    @Test
    void mapToDestinationDTOGet() {
        // Given
        Destination destination = new Destination("country", "city");
        List<HotelDTOGet> hotelDTOGetList = new ArrayList<>();

        DestinationDTOGet destinationDTOGet = new DestinationDTOGet("country", "city", hotelDTOGetList);
        // When
        DestinationDTOGet mappedDestinationDTOget = destinationMapper.mapToDestinationDTOGet(destination);
        // Then
        Assertions.assertEquals(destinationDTOGet, mappedDestinationDTOget);
    }
    @Test
    void mapToDestinationDTOGetList() {
        // Given
        List<Destination> destinationList = Arrays.asList(new Destination("country", "city"));
        List<HotelDTOGet> hotelDTOGetList = new ArrayList<>();
        List<DestinationDTOGet> destinationDTOList = Arrays.asList(new DestinationDTOGet( "country", "city",hotelDTOGetList));
        // When
        List<DestinationDTOGet> mappedDestinationDTOGetList = destinationMapper.mapToDestinationDTOGetList(destinationList);
        // Then
        Assertions.assertEquals(destinationDTOList, mappedDestinationDTOGetList);
    }

}