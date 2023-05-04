package com.travel_agency.service;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.dto.DestinationDTO;
import com.travel_agency.domain.dto.get.DestinationDTOGet;
import com.travel_agency.exception.DestinationNotFoundException;
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

    public List<DestinationDTOGet> getDestinations() {
        List<Destination> destinationList = destinationRepository.findAll();
        return destinationMapper.mapToDestinationDTOGetList(destinationList);
    }
    public List<DestinationDTO> showDestinations() {
        List<Destination> destinationList = destinationRepository.findAll();
        return destinationMapper.mapToDestinationDTOList(destinationList);
    }
    public void deleteDestination(Long destinationId) throws DestinationNotFoundException {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(DestinationNotFoundException::new);
        destinationRepository.delete(destination);
    }

    public DestinationDTO modifyDestination(final DestinationDTO destinationDTO) throws DestinationNotFoundException {
        Destination destination = destinationRepository.findById(destinationDTO.destinationId()).orElseThrow(DestinationNotFoundException::new);
        destination.setCountry(destinationDTO.country());
        destination.setCity(destinationDTO.city());
        destination.setCountry(destinationDTO.postcode());
        destinationRepository.save(destination);
        return destinationMapper.mapToDestinationDTO(destination);
    }
}
