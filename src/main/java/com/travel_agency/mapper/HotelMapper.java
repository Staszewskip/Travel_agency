package com.travel_agency.mapper;

import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelMapper {


    public Hotel mapToHotel(HotelDTO hotelDTO) {
        return new Hotel(
                hotelDTO.name(),
                hotelDTO.destination());
    }

    public HotelDTO mapToHotelDTO(Hotel hotel) {
        return new HotelDTO(
                hotel.getHotelId(),
                hotel.getName(),
                hotel.getDestination());
    }

    public List<HotelDTO> mapToHotelDTOList(List<Hotel> hotelList) {
        return hotelList.stream()
                .map(this::mapToHotelDTO)
                .collect(Collectors.toList());
    }
}
