package com.travel_agency.mapper;

import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.domain.dto.get.HotelDTOGet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelMapper {
    public HotelDTO mapToHotelDTO(Hotel hotel) {
        return new HotelDTO(
                hotel.getHotelId(),
                hotel.getName(),
                hotel.getDestination().getDestinationId(),
                hotel.getUnitPrice());
    }

    public HotelDTOGet mapToHotelDTOGet(Hotel hotel) {
        return new HotelDTOGet(
                hotel.getDestination().getCountry(),
                hotel.getDestination().getCity(),
                hotel.getName(),
                hotel.getUnitPrice());
    }

    public List<HotelDTO> mapToHotelDTOList(List<Hotel> hotelList) {
        return hotelList.stream()
                .map(this::mapToHotelDTO)
                .collect(Collectors.toList());
    }

    public List<HotelDTOGet> mapToHotelDTOGetList(List<Hotel> hotelList) {
        return hotelList.stream()
                .map(this::mapToHotelDTOGet)
                .collect(Collectors.toList());
    }
}
