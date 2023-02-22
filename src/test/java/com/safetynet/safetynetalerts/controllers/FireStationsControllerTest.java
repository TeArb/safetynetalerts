package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.models.FireStations;
import com.safetynet.safetynetalerts.repositories.FireStationsRepository;
import com.safetynet.safetynetalerts.servicesImplement.FireStationsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FireStationsControllerTest {
    @MockBean
    protected FireStationsServiceImpl fireStationsServiceImpl;
    @Autowired
    private FireStationsRepository fireStationsRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void validValue_thenReturns200() throws Exception {
        FireStations newFireStations = new FireStations("15 St James", "5");

        mockMvc.perform(post("/firestation/")
                        .contentType("application/json")
                        .param("FireStations", "newFireStations")
                        .content(objectMapper.writeValueAsString(newFireStations)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void nullValue_thenReturns400() throws Exception {
        mockMvc.perform(post("/firestation/")
                        .contentType("application/json")
                        .param("FireStations", "newFireStations")
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getFireStations() throws Exception {
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();

        when(fireStationsServiceImpl.getFireStations()).thenReturn(fireStationsList);

        mockMvc.perform(get("/firestation/")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(fireStationsServiceImpl).getFireStations();
    }

    @Test
    void addFireStations() throws Exception {
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        FireStations newFireStations = new FireStations("15 St James", "5");

        when(fireStationsServiceImpl.addFireStations(newFireStations)).thenReturn(fireStationsList);

        mockMvc.perform(post("/firestation/")
                        .contentType("application/json")
                        .param("FireStations", "newFireStations")
                        .content(objectMapper.writeValueAsString(newFireStations)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(fireStationsServiceImpl, times(1)).addFireStations(newFireStations);
    }

    @Test
    void updateFireStations() throws Exception {
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        FireStations newFireStations = new FireStations("15 St James", "5");
        String address = "1509 Culver St";

        when(fireStationsServiceImpl.updateFireStations(newFireStations, address)).thenReturn(fireStationsList);

        mockMvc.perform(put("/firestation/" + address)
                        .contentType("application/json")
                        .param("FireStations", "newFireStations")
                        .content(objectMapper.writeValueAsString(newFireStations)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(fireStationsServiceImpl, times(1)).updateFireStations(newFireStations, address);
    }

    @Test
    void deleteFireStations() throws Exception {
        FireStations removeFireStations = new FireStations("1509 Culver St", "3");
        String deleteFireStation = "Fire station deleted";

        when(fireStationsServiceImpl.deleteFireStations(removeFireStations)).thenReturn(deleteFireStation);

        mockMvc.perform(delete("/firestation/")
                        .contentType("application/json")
                        .param("FireStations", "removeFireStations")
                        .content(objectMapper.writeValueAsString(removeFireStations)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(fireStationsServiceImpl, times(1)).deleteFireStations(removeFireStations);
    }
}