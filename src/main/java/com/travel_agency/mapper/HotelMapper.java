package com.travel_agency.mapper;

import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.HotelDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelMapper {
    public Hotel mapToHotel(HotelDTO hotelDTO) {
        return new Hotel(
                hotelDTO.getName());
    }

    public HotelDTO mapToHotelDTO(Hotel hotel) {
        return new HotelDTO(
                hotel.getHotelId(),
                hotel.getName());
    }

    public List<HotelDTO> mapToHotelDTOList(List<Hotel> hotelList) {
        return hotelList.stream()
                .map(this::mapToHotelDTO)
                .collect(Collectors.toList());
    }
}
