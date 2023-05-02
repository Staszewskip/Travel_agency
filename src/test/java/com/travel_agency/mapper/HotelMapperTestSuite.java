package com.travel_agency.mapper;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.HotelDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HotelMapperTestSuite {
    HotelMapper hotelMapper = new HotelMapper();

    @Test
    void testMapToHotel() {
        // Given
        Hotel hotel = new Hotel("Hotel_name", new Destination("country", "city", "postcode"));
        HotelDTO hotelDTO = new HotelDTO(null, "Hotel_name", new Destination("country", "city", "postcode"));

        // When
        Hotel mappedHotel = hotelMapper.mapToHotel(hotelDTO);
        HotelDTO mappedHotelDTO = hotelMapper.mapToHotelDTO(hotel);
        // Then
        Assertions.assertAll(
                () -> assertEquals(hotel, mappedHotel),
                () -> assertEquals(hotelDTO, mappedHotelDTO)
        );
    }

}