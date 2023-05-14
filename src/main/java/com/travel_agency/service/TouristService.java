package com.travel_agency.service;

import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.domain.dto.get.TouristDTOGet;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.mapper.TouristMapper;
import com.travel_agency.repository.TouristRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TouristService {
    private final TouristRepository touristRepository;
    private final TouristMapper touristMapper;
//    private final PasswordEncoder bCryptPasswordEncoder;

    public void saveTourist(final TouristDTO touristDTO) {
        Tourist tourist = touristMapper.mapToTourist(touristDTO);
//        String hashedPassword = bCryptPasswordEncoder.encode(tourist.getPassword());
//        tourist.setPassword(hashedPassword);
        touristRepository.save(tourist);
    }

    public List<TouristDTOGet> showAllTourists() {
        List<Tourist> touristList = touristRepository.findAll();
        return touristMapper.mapToTouristDTOGetList(touristList);
    }

    public void deleteTourist(Long touristId) throws TouristNotFoundException {
        Tourist tourist = touristRepository.findById(touristId).orElseThrow(TouristNotFoundException::new);
        touristRepository.delete(tourist);
    }

    public TouristDTOGet modifyTourist(final TouristDTO touristDTO) throws TouristNotFoundException {
        Tourist tourist = touristRepository.findById(touristDTO.touristId()).orElseThrow(TouristNotFoundException::new);
        tourist.setFirstname(touristDTO.firstname());
        tourist.setLastname(touristDTO.lastname());
        tourist.setLogin(touristDTO.login());
        tourist.setPassword(touristDTO.password());
        tourist.setEmail(touristDTO.email());
        tourist.setPhoneNumber(touristDTO.phoneNumber());
        touristRepository.save(tourist);
        return touristMapper.mapToTouristDTOGet(tourist);
    }
}
