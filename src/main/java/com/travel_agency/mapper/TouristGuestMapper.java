package com.travel_agency.mapper;

import com.travel_agency.domain.TouristGuest;
import com.travel_agency.domain.dto.TouristGuestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TouristGuestMapper {
    ModelMapper modelMapper = new ModelMapper();

    public TouristGuest mapToTouristGuest(TouristGuestDTO touristGuestDTO){
        TouristGuest touristGuest = modelMapper.map(touristGuestDTO, TouristGuest.class);
        return touristGuest;
    }
}
