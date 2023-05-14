package com.travel_agency.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.service.TouristService;
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

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TouristController.class)
class TouristControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TouristService touristService;

    @Test
    void addTourist() throws Exception {
        // Given

        TouristDTO touristDTO = new TouristDTO(1L, "name", "lastname", LocalDate.now(), "login", "password", "email", 123456);
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        String jsonContent = mapper.writeValueAsString(touristDTO);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tourists")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    void modifyTourist() throws Exception {
        // Given
        TouristDTO touristDTO = new TouristDTO(1L, "updated_name", "updated_lastname", LocalDate.now(), "login", "password", "email", 123456);
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        String jsonContent = mapper.writeValueAsString(touristDTO);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tourists")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonContent)
                )
                .andExpect(status().is(200));
    }

    @Test
    void getAllTourists() throws Exception {
        // Given
        when(touristService.showAllTourists()).thenReturn(List.of());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tourists/admin")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void deleteTourist() throws Exception {
        // Given
        doNothing().when(touristService).deleteTourist(1L);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tourists/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200));
    }
}