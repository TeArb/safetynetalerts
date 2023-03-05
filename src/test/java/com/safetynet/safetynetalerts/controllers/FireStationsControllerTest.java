package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.models.FireStations;
import com.safetynet.safetynetalerts.servicesImplement.FireStationsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FireStationsControllerTest {
    @Autowired
    private FireStationsController fireStationsController;
    @MockBean
    protected FireStationsServiceImpl fireStationsService;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void nullValue_thenReturns400() throws Exception {
        mockMvc.perform(post("/firestation/")
                        .contentType("application/json")
                        .param("Firestations", "newFirestations")
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getFireStations() throws Exception {
        List<FireStations> fireStationsList = new ArrayList<>();
        fireStationsList.add(new FireStations("15 St James", "5"));

        when(fireStationsService.getFireStations()).thenReturn(fireStationsList);

        mockMvc.perform(get("/firestation/")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        List<FireStations> stationsList = fireStationsController.getFireStations();
        assertEquals(stationsList, fireStationsList);
    }

    @Test
    void addFireStations() throws Exception {
        FireStations newFireStations = new FireStations("15 St James", "5");

        List<FireStations> fireStationsList = new ArrayList<>();
        fireStationsList.add(newFireStations);

        when(fireStationsService.addFireStations(newFireStations))
                .thenReturn(fireStationsList);

        mockMvc.perform(post("/firestation/")
                        .contentType("application/json")
                        .param("Firestation", String.valueOf(newFireStations))
                        .content(objectMapper.writeValueAsString(newFireStations)))
                .andDo(print())
                .andExpect(status().isOk());

        List<FireStations> stationsList = fireStationsController.addFireStations(newFireStations);
        assertEquals(stationsList, fireStationsList);
    }

    @Test
    void updateFireStations() throws Exception {
        FireStations newFireStations = new FireStations("15 St James", "5");
        String address = "1509 Culver St";

        List<FireStations> fireStationsList = new ArrayList<>();
        fireStationsList.add(newFireStations);

        when(fireStationsService.updateFireStations(newFireStations, address))
                .thenReturn(fireStationsList);

        mockMvc.perform(put("/firestation/{address}", address)
                        .contentType("application/json")
                        .param("newFirestation", String.valueOf(newFireStations))
                        .content(objectMapper.writeValueAsString(newFireStations)))
                .andDo(print())
                .andExpect(status().isOk());

        List<FireStations> stationsList = fireStationsController.updateFireStations(newFireStations, address);
        assertEquals(stationsList, fireStationsList);
    }

    @Test
    void deleteFireStations() throws Exception {
        FireStations removeFireStations = new FireStations("1509 Culver St", "3");

        when(fireStationsService.deleteFireStations(removeFireStations))
                .thenReturn(null);

        mockMvc.perform(delete("/firestation/")
                        .contentType("application/json")
                        .param("newFireStations", String.valueOf(removeFireStations))
                        .content(objectMapper.writeValueAsString(removeFireStations)))
                .andDo(print())
                .andExpect(status().isOk());

        String fireStationsString = fireStationsController.deleteFireStations(removeFireStations);
        assertNull(fireStationsString);
    }
}