package com.travel_agency.controller;

import com.google.gson.Gson;
import com.travel_agency.domain.Destination;
import com.travel_agency.domain.dto.HotelDTO;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.repository.DestinationRepository;
import com.travel_agency.repository.HotelRepository;
import com.travel_agency.service.HotelService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(HotelController.class)
class HotelControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelService hotelService;
    @MockBean
    private DestinationRepository destinationRepository;


    @Test
    void addHotel() throws Exception {
        // Given
        Destination destination = new Destination("country", "city", "postcode");
        HotelDTO hotelDTO = new HotelDTO(1L, "Hotel_name", destination.getDestinationId(),100);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(hotelDTO);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/hotels")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    void modifyHotel() throws Exception {
//        // Given
//        Destination destination = new Destination("country", "city", "postcode");
//        Destination savedDestination = destinationRepository.save(destination);
//        HotelDTO hotelDTO = new HotelDTO(1L, "Updated_Hotel_name", savedDestination.getDestinationId());
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(hotelDTO);
//        // When & Then
//        mockMvc
//                .perform(MockMvcRequestBuilders
//                        .put("/v1/tourists")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(jsonContent)
//                )
//                .andExpect(status().is(200));
    }

    @Test
    void getHotels() throws Exception {
        // Given
        when(hotelService.getHotels()).thenReturn(List.of());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/hotels")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void showHotels() throws Exception {
        // Given
        when(hotelService.showHotels()).thenReturn(List.of());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/hotels/admin")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void deleteHotel() throws Exception {
        // Given
        doNothing().when(hotelService).deleteHotel(1L);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/hotels/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200));
    }
}