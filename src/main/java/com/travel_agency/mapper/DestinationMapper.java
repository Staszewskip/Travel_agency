package com.travel_agency.mapper;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.DestinationDTO;
import com.travel_agency.domain.dto.get.DestinationDTOGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationMapper {
    HotelMapper hotelMapper = new HotelMapper();

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

    public DestinationDTOGet mapToDestinationDTOGet(Destination destination) {
        return new DestinationDTOGet(
                destination.getCountry(),
                destination.getCity(),
                destination.getPostcode(),
                hotelMapper.mapToHotelDTOGetList(destination.getHotelList()));
    }

    public List<DestinationDTO> mapToDestinationDTOList(List<Destination> destinationList) {
        return destinationList.stream()
                .map(this::mapToDestinationDTO)
                .collect(Collectors.toList());
    }

    public List<DestinationDTOGet> mapToDestinationDTOGetList(List<Destination> destinationList) {
        return destinationList.stream()
                .map(this::mapToDestinationDTOGet)
                .collect(Collectors.toList());
    }
}
