package com.travel_agency.controller;

import com.google.gson.Gson;
import com.travel_agency.domain.dto.TouristDTO;
import com.travel_agency.domain.dto.TouristDTOGet;
import com.travel_agency.exception.TouristNotFoundException;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        TouristDTO touristDTO = new TouristDTO(1L, "name", "lastname", true, "login", "password", "email", 123456);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(touristDTO);
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
        TouristDTO touristDTO = new TouristDTO(1L, "updated_name", "updated_lastname", true, "login", "password", "email", 123456);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(touristDTO);
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
    void getTourist() throws Exception {
        // Given
        when(touristService.findTourist(1L)).thenReturn(new TouristDTOGet(1L, "firstname", "lastname", false));
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tourists/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.touristId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("firstname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.is("lastname")));
    }

    @Test
    void getAllTourists() throws Exception {
        // Given
        when(touristService.findAllTourists()).thenReturn(List.of());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tourists")
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

    @Test
    void shouldThrowException() throws Exception {
        // Given
        String exceptionParam = "dummy";
        when(touristService.findTourist(10L)).thenThrow(TouristNotFoundException.class);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tourists/10", exceptionParam)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }
}