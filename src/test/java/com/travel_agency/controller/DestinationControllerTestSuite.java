package com.travel_agency.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel_agency.domain.dto.DestinationDTO;
import com.travel_agency.service.DestinationService;
import com.travel_agency.service.WeatherService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(DestinationController.class)
class DestinationControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DestinationService destinationService;

    @MockBean
    private WeatherService weatherService;

    @Test
    void addDestination() throws Exception {
        // Given
        DestinationDTO destinationDTO = new DestinationDTO(null,"country", "city", "postcode");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(destinationDTO);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/destinations")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    void getDestinations() throws Exception {
        // Given
        when(destinationService.getDestinations()).thenReturn(List.of());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/destinations")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void adminShowDestinations() throws Exception {
        // Given
        when(destinationService.showDestinations()).thenReturn(List.of());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/destinations/admin")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void deleteDestination() throws Exception {
        // Given
        doNothing().when(destinationService).deleteDestination(1L);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/destinations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200));
    }

    @Test
    void getForecast2() {
    }
}