package com.travel_agency.service;

import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.mapper.TouristMapper;
import com.travel_agency.repository.TouristRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TouristService {
    private final TouristRepository touristRepository;
    private final TouristMapper touristMapper;

    public Tourist saveTourist(final TouristDTO touristDTO) {
        Tourist tourist = touristMapper.mapToTourist(touristDTO);
        return touristRepository.save(tourist);
    }

    public TouristDTO findTourist(Long touristId) throws TouristNotFoundException {
        Tourist tourist = touristRepository.findById(touristId).orElseThrow(TouristNotFoundException::new);
        return touristMapper.mapToTouristDTO(tourist);
    }

    public List<TouristDTO> findAllTourists() {
        List<Tourist> touristList = touristRepository.findAll();
        return touristMapper.mapToTouristDTOList(touristList);
    }

    public void deleteTourist(Long touristId) throws TouristNotFoundException {
        Tourist tourist = touristRepository.findById(touristId).orElseThrow(TouristNotFoundException::new);
        touristRepository.delete(tourist);
    }

    public TouristDTO modifyTourist(final TouristDTO touristDTO) throws TouristNotFoundException {
        Tourist tourist = touristRepository.findById(touristDTO.getTouristId()).orElseThrow(TouristNotFoundException::new);
        tourist.setFirstname(touristDTO.getFirstname());
        tourist.setLastname(touristDTO.getLastname());
        tourist.setLogin(touristDTO.getLogin());
        tourist.setPassword(touristDTO.getPassword());
        tourist.setEmail(touristDTO.getEmail());
        tourist.setPhoneNumber(touristDTO.getPhoneNumber());
        touristRepository.save(tourist);
        return touristMapper.mapToTouristDTO(tourist);
    }

}
