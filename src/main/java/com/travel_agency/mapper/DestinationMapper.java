package com.travel_agency.mapper;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.dto.DestinationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationMapper {
    public Destination mapToDestination(DestinationDTO destinationDTO) {
        return new Destination(
                destinationDTO.country(),
                destinationDTO.city(),
                destinationDTO.postcode());
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
