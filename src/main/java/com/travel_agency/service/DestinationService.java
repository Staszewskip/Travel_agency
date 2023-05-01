package com.travel_agency.service;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.dto.DestinationDTO;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.exception.DestinationNotFoundException;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.mapper.DestinationMapper;
import com.travel_agency.repository.DestinationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DestinationService {
    private final DestinationMapper destinationMapper;
    private final DestinationRepository destinationRepository;

    public Destination saveDestination(final DestinationDTO destinationDTO) {
        Destination destination = destinationMapper.mapToDestination(destinationDTO);
        return destinationRepository.save(destination);
    }

    public List<DestinationDTO> findDestinations() {
        List<Destination> destinationList = destinationRepository.findAll();
        return destinationMapper.mapToDestinationDTOList(destinationList);
    }

    public void deleteDestination(Long destinationId) throws DestinationNotFoundException {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(DestinationNotFoundException::new);
        destinationRepository.delete(destination);
    }

    public DestinationDTO modifyDestination(final DestinationDTO destinationDTO) throws DestinationNotFoundException {
        Destination destination = destinationRepository.findById(destinationDTO.getDestinationId()).orElseThrow(DestinationNotFoundException::new);
        destination.setCountry(destinationDTO.getCountry());
        destination.setCity(destinationDTO.getCity());
        destination.setCountry(destinationDTO.getPostcode());
        destinationRepository.save(destination);
        return destinationMapper.mapToDestinationDTO(destination);
    }
}
