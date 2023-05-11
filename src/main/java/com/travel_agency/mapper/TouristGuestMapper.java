package com.travel_agency.mapper;

import com.travel_agency.domain.TouristGuest;
import com.travel_agency.domain.dto.TouristGuestDTO;
import org.springframework.stereotype.Service;

@Service
public class TouristGuestMapper {

    public TouristGuest mapToTouristGuest(TouristGuestDTO touristGuestDTO){
       return new TouristGuest(
               touristGuestDTO.firstname(),
               touristGuestDTO.lastname(),
               touristGuestDTO.isAdult());
    }
}
