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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FireStationsControllerTest {
    @MockBean
    protected FireStationsController fireStationsController;
    @Autowired
    private FireStationsServiceImpl fireStationsServiceImpl;
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
        when(fireStationsController.getFireStations()).thenReturn(fireStationsServiceImpl.getFireStations());

        mockMvc.perform(get("/firestation/")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(fireStationsController).getFireStations();
    }

    @Test
    void addFireStations() throws Exception {
        FireStations newFireStations = new FireStations("15 St James", "5");

        when(fireStationsController.addFireStations(newFireStations))
                .thenReturn(fireStationsServiceImpl.addFireStations(newFireStations));

        mockMvc.perform(post("/firestation/")
                        .contentType("application/json")
                        .param("Firestation", String.valueOf(newFireStations))
                        .content(objectMapper.writeValueAsString(newFireStations)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(fireStationsController, times(1)).addFireStations(newFireStations);
    }

    @Test
    void updateFireStations() throws Exception {
        FireStations newFireStations = new FireStations("15 St James", "5");
        String address = "1509 Culver St";

        when(fireStationsController.updateFireStations(newFireStations, address))
                .thenReturn(fireStationsServiceImpl.updateFireStations(newFireStations, address));

        mockMvc.perform(put("/firestation/{address}", address)
                        .contentType("application/json")
                        .param("newFirestation", String.valueOf(newFireStations))
                        .content(objectMapper.writeValueAsString(newFireStations)))
                .andDo(print())
                .andExpect(status().isOk());


        verify(fireStationsController, times(1))
                .updateFireStations(newFireStations, address);
    }

    @Test
    void deleteFireStations() throws Exception {
        FireStations newFireStations = new FireStations("1509 Culver St", "3");

        when(fireStationsController.deleteFireStations(newFireStations))
                .thenReturn(fireStationsServiceImpl.deleteFireStations(newFireStations));

        mockMvc.perform(delete("/firestation/")
                        .contentType("application/json")
                        .param("newFireStations", String.valueOf(newFireStations))
                        .content(objectMapper.writeValueAsString(newFireStations)))
                .andDo(print())
                .andExpect(status().isOk());


        verify(fireStationsController, times(1))
                .deleteFireStations(newFireStations);
    }
}