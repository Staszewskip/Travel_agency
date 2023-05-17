package com.travel_agency.service;

import com.travel_agency.domain.Destination;
import com.travel_agency.domain.Hotel;
import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.domain.dto.get.HotelDTOGet;
import com.travel_agency.exception.DestinationNotFoundException;
import com.travel_agency.exception.HotelNotFoundException;
import com.travel_agency.mapper.HotelMapper;
import com.travel_agency.repository.DestinationRepository;
import com.travel_agency.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;
    private final DestinationRepository destinationRepository;

    public void saveHotel(final HotelDTO hotelDTO) throws DestinationNotFoundException {
        Destination destination = destinationRepository.findById(hotelDTO.destinationID()).orElseThrow(DestinationNotFoundException::new);
        Hotel hotel = new Hotel(hotelDTO.name(), destination, hotelDTO.unitPrice());
        hotelRepository.save(hotel);
    }

    public List<HotelDTOGet> getHotels() {
        List<Hotel> HotelList = hotelRepository.findAll();
        return hotelMapper.mapToHotelDTOGetList(HotelList);
    }

    public List<HotelDTO> showHotels() {
        List<Hotel> HotelList = hotelRepository.findAll();
        return hotelMapper.mapToHotelDTOList(HotelList);
    }

    @Transactional
    public void deleteHotel(Long hotelId) throws HotelNotFoundException {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(HotelNotFoundException::new);
        hotelRepository.delete(hotel);
    }

    @Transactional
    public HotelDTO modifyHotel(final HotelDTO hotelDTO) throws HotelNotFoundException {
        Hotel hotel = hotelRepository.findById(hotelDTO.hotelId()).orElseThrow(HotelNotFoundException::new);
        hotel.setName(hotelDTO.name());
        hotelRepository.save(hotel);
        return hotelMapper.mapToHotelDTO(hotel);
    }

    @Transactional
    public void createExampleData() {
        Destination destination = new Destination("Spain", "Barcelona");
        Destination destination2 = new Destination("France", "Paris");
        Destination destination3 = new Destination("Great Britain", "London");
        Destination savedDestination = destinationRepository.save(destination);
        Destination savedDestination2 = destinationRepository.save(destination2);
        Destination savedDestination3 = destinationRepository.save(destination3);
        List<Hotel> hotelList = Arrays.asList(
                new Hotel("FCB Hotel", savedDestination, 200),
                new Hotel("PSG Hotel", savedDestination2, 250),
                new Hotel("Arsenal Hotel", savedDestination3, 300),
                new Hotel("Chelsea Hotel", savedDestination3, 300));
        hotelRepository.saveAll(hotelList);
    }
}
