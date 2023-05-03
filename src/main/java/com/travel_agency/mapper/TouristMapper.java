package com.travel_agency.mapper;

import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.domain.dto.TouristDTOGet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristMapper {

    public Tourist mapToTourist(TouristDTO touristDTO) {
        return new Tourist(
                touristDTO.firstname(),
                touristDTO.lastname(),
                touristDTO.isAdult(),
                touristDTO.login(),
                touristDTO.password(),
                touristDTO.email(),
                touristDTO.phoneNumber()
        );
    }

    public TouristDTO mapToTouristDTO(Tourist tourist) {
        return new TouristDTO(
                tourist.getTouristId(),
                tourist.getFirstname(),
                tourist.getLastname(),
                tourist.isAdult(),
                tourist.getLogin(),
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
    public TouristDTOGet mapToTouristDTOget(Tourist tourist) {
//       TouristDTOGet touristDTOGet = modelMapper.map(tourist, TouristDTOGet.class);
//       return touristDTOGet;
        return new TouristDTOGet(tourist.getTouristId(), tourist.getFirstname(), tourist.getLastname(), tourist.isAdult());
    }
    public List<TouristDTOGet> mapToTouristDTOGetList(List<Tourist> touristList) {
        return touristList.stream()
                .map(this::mapToTouristDTOget)
                .collect(Collectors.toList());
    }
}
