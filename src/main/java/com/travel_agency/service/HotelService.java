package com.travel_agency.service;

import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.exception.HotelNotFoundException;
import com.travel_agency.mapper.HotelMapper;
import com.travel_agency.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;

    public void saveHotel(final HotelDTO hotelDTO) {
        Hotel hotel = hotelMapper.mapToHotel(hotelDTO);
        hotelRepository.save(hotel);
    }

    public List<HotelDTO> findHotels() {
        List<Hotel> HotelList = hotelRepository.findAll();
        return hotelMapper.mapToHotelDTOList(HotelList);
    }

    public void deleteHotel(Long hotelId) throws HotelNotFoundException {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(HotelNotFoundException::new);
        hotelRepository.delete(hotel);
    }

    public HotelDTO modifyHotel(final HotelDTO hotelDTO) throws HotelNotFoundException{
        Hotel hotel = hotelRepository.findById(hotelDTO.getHotelId()).orElseThrow(HotelNotFoundException::new);
        hotel.setName(hotelDTO.getName());
        hotelRepository.save(hotel);
        return hotelMapper.mapToHotelDTO(hotel);
    }

}
