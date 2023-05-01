package com.travel_agency.mapper;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.dto.DestinationDTO;
import com.travel_agency.domain.dto.TouristDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationMapper {
    public Destination mapToDestination(DestinationDTO destinationDTO) {
        return new Destination(
                destinationDTO.getCountry(),
                destinationDTO.getCity(),
                destinationDTO.getPostcode());
    }
    public DestinationDTO mapToDestinationDTO(Destination destination) {
        return new DestinationDTO(
                destination.getDestinationId(),
                destination.getCountry(),
                destination.getCity(),
                destination.getPostcode());
    }
    public List<DestinationDTO> mapToDestinationDTOList(List<Destination> destinationList) {
        return destinationList.stream()
                .map(this::mapToDestinationDTO)
                .collect(Collectors.toList());
    }
}
