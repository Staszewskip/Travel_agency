package com.travel_agency.mapper;

import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.dto.TouristDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristMapper {
    public Tourist mapToTourist(TouristDTO touristDTO) {
        return new Tourist(
                touristDTO.getFirstname(),
                touristDTO.getLastname(),
                touristDTO.isAdult(),
                touristDTO.getEmail(),
                touristDTO.getPassword(),
                touristDTO.getEmail(),
                touristDTO.getPhoneNumber()
        );
    }

    public TouristDTO mapToTouristDTO(Tourist tourist) {
        return new TouristDTO(
                tourist.getTouristId(),
                tourist.getFirstname(),
                tourist.getLastname(),
                tourist.isAdult(),
                tourist.getEmail(),
                tourist.getPassword(),
                tourist.getEmail(),
                tourist.getPhoneNumber()
        );
    }

    public List<TouristDTO> mapToTouristDTOList(List<Tourist> touristList) {
        return touristList.stream()
                .map(this::mapToTouristDTO)
                .collect(Collectors.toList());
    }
}
