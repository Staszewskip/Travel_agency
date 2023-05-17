package com.travel_agency.mapper;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.exception.DestinationNotFoundException;
import com.travel_agency.repository.DestinationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HotelMapperTestSuite {

    HotelMapper hotelMapper = new HotelMapper();

    @Test
    void testMapToHotelDTO() {
        // Given
        Hotel hotel = new Hotel("Hotel_name", new Destination("country", "city"),100);
        HotelDTO hotelDTO = new HotelDTO(null, "Hotel_name", null,100L);
        // When
        HotelDTO mappedHotelDTO = hotelMapper.mapToHotelDTO(hotel);
        // Then
        Assertions.assertEquals(hotelDTO, mappedHotelDTO);
    }

    @Test
    void testMapToHotelDTOList() {
        // Given
        List<Hotel> hotelList = Arrays.asList(new Hotel("Hotel_name", new Destination("country", "city"),100));
        List<HotelDTO> hotelDTOList = Arrays.asList(new HotelDTO(null, "Hotel_name", null,100L));
        // When
        List<HotelDTO> mappedHotelDTOList = hotelMapper.mapToHotelDTOList(hotelList);
        // Then
        Assertions.assertEquals(hotelDTOList, mappedHotelDTOList);
    }
}