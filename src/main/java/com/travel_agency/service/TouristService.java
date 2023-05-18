package com.travel_agency.service;

import com.travel_agency.domain.Tourist;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.domain.dto.TouristLoggedDTO;
import com.travel_agency.domain.dto.TouristLoggingDTO;
import com.travel_agency.domain.dto.get.TouristDTOGet;
import com.travel_agency.exception.LoginAlreadyUsedException;
import com.travel_agency.exception.TouristNotFoundException;
import com.travel_agency.exception.WrongPasswordException;
import com.travel_agency.mapper.TouristMapper;
import com.travel_agency.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TouristService {
    private final TouristRepository touristRepository;
    private final TouristMapper touristMapper;


    public void saveTourist(final TouristDTO touristDTO) throws LoginAlreadyUsedException {
        String passwordSalt = RandomStringUtils.random(32);
        String passwordHash = DigestUtils.sha512Hex(touristDTO.password() + passwordSalt);
        if (existsByLogin(touristDTO.login())) {
            throw new LoginAlreadyUsedException();
        }
        Tourist tourist = new Tourist(
                touristDTO.firstname(),
                touristDTO.lastname(),
                touristDTO.birthdate(),
                touristDTO.login(),
                passwordSalt,
                passwordHash,
                touristDTO.email(),
                touristDTO.phoneNumber(),
                touristDTO.role()
        );
        touristRepository.save(tourist);
    }

    public TouristLoggedDTO login(TouristLoggingDTO touristLoggingDTO) throws TouristNotFoundException, WrongPasswordException {
        Tourist tourist = touristRepository.findByLogin(touristLoggingDTO.login()).orElseThrow(TouristNotFoundException::new);
        if (DigestUtils.sha512Hex(touristLoggingDTO.password() + tourist.getPassword()).equals(tourist.getPasswordHash())) {
            return new TouristLoggedDTO(tourist.getLogin(), tourist.getRole());
        } else {
            throw new WrongPasswordException();
        }
    }

    public TouristDTOGet findByLogin(String login) throws TouristNotFoundException {
        return touristMapper.mapToTouristDTOGet(touristRepository.findByLogin(login).orElseThrow(TouristNotFoundException::new));
    }

    public boolean existsByLogin(String login) {
        return touristRepository.existsByLogin(login);
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
