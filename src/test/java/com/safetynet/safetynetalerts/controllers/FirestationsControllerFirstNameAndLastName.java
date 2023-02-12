package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.repositories.FirestationsRepository;
import com.safetynet.safetynetalerts.servicesImplement.FirestationsServiceImpl;
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
class FirestationsControllerFirstNameAndLastName {
    @MockBean
    protected FirestationsServiceImpl firestationsServiceImpl;
    @Autowired
    private FirestationsRepository firestationsRepository;
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
        Firestations newFirestations = new Firestations("15 St James", "5");

        mockMvc.perform(post("/firestations/")
                        .contentType("application/json")
                        .param("Firestations", "newFirestations")
                        .content(objectMapper.writeValueAsString(newFirestations)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void nullValue_thenReturns400() throws Exception {
        mockMvc.perform(post("/firestations/")
                        .contentType("application/json")
                        .param("Firestations", "newFirestations")
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getFirestations() throws Exception {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();

        //given(firestationsServiceImpl.getFirestations()).willReturn(firestationsList);

        when(firestationsServiceImpl.getFirestations()).thenReturn(firestationsList);
        mockMvc.perform(get("/firestations/")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(firestationsServiceImpl).getFirestations();
    }

    @Test
    void addFirestations() throws Exception {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        Firestations newFirestations = new Firestations("15 St James", "5");

        when(firestationsServiceImpl.addFirestations(newFirestations)).thenReturn(firestationsList);

        mockMvc.perform(post("/firestations/")
                        .contentType("application/json")
                        .param("Firestations", "newFirestations")
                        .content(objectMapper.writeValueAsString(newFirestations)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(firestationsServiceImpl, times(1)).addFirestations(newFirestations);
    }

    @Test
    void updateFirestations() throws Exception {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        Firestations newFirestations = new Firestations("15 St James", "5");
        String address = "1509 Culver St";

        when(firestationsServiceImpl.updateFirestations(newFirestations, address)).thenReturn(firestationsList);

        mockMvc.perform(put("/firestations/" + address)
                        .contentType("application/json")
                        .param("Firestations", "newFirestations")
                        .content(objectMapper.writeValueAsString(newFirestations)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(firestationsServiceImpl, times(1)).updateFirestations(newFirestations, address);
    }

    @Test
    void deleteFirestations() throws Exception {
        Firestations removeFirestations = new Firestations("1509 Culver St", "3");
        String deleteFirestation = "Fire station deleted";

        when(firestationsServiceImpl.deleteFirestations(removeFirestations)).thenReturn(deleteFirestation);

        mockMvc.perform(delete("/firestations/")
                        .contentType("application/json")
                        .param("Firestations", "removeFirestations")
                        .content(objectMapper.writeValueAsString(removeFirestations)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(firestationsServiceImpl, times(1)).deleteFirestations(removeFirestations);
    }
}