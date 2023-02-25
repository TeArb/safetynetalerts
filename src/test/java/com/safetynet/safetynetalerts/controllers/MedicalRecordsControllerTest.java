package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.servicesImplement.MedicalRecordsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MedicalRecordsControllerTest {
    @MockBean
    protected MedicalRecordsController medicalRecordsController;
    @Autowired
    private MedicalRecordsServiceImpl medicalRecordsServiceImpl;
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
        mockMvc.perform(post("/medicalrecord/")
                        .contentType("application/json")
                        .param("MedicalRecords", "newMedicalRecords")
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getMedicalRecords() throws Exception {
        when(medicalRecordsController.getMedicalRecords()).thenReturn(medicalRecordsServiceImpl.getMedicalRecords());

        mockMvc.perform(get("/medicalrecord/")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(medicalRecordsController).getMedicalRecords();
    }

    @Test
    void addMedicalRecords() throws Exception {
        List<String> medication = Arrays.asList("pharmacol:5000mg",
                "terazine:10mg");
        List<String> allergies = List.of("peanut");
        Date birthdate = new Date("03/06/1989");
        MedicalRecords newMedicalRecords = new MedicalRecords("Jake",
                "Doe", birthdate, medication, allergies);

        when(medicalRecordsController.addMedicalRecords(newMedicalRecords))
                .thenReturn(medicalRecordsServiceImpl.addMedicalRecords(newMedicalRecords));

        mockMvc.perform(post("/medicalrecord/")
                        .contentType("application/json")
                        .param("MedicalRecords", String.valueOf(newMedicalRecords))
                        .content(objectMapper.writeValueAsString(newMedicalRecords)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(medicalRecordsController, times(1)).addMedicalRecords(newMedicalRecords);
    }

    @Test
    void updateMedicalRecords() throws Exception {
        List<String> medication = Arrays.asList("pharmacol:5000mg",
                "terazine:10mg");
        List<String> allergies = List.of("peanut");
        Date birthdate = new Date("02/06/1984");
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords = new MedicalRecords(firstName,
                lastName, birthdate, medication, allergies);

        when(medicalRecordsController.updateMedicalRecords(newMedicalRecords, firstName, lastName))
                .thenReturn(medicalRecordsServiceImpl.updateMedicalRecords(newMedicalRecords, firstName, lastName));

            mockMvc.perform(put("/medicalrecord/{firstName}/{lastName}", firstName, lastName)
                        .contentType("application/json")
                        .param("newMedicalRecords", String.valueOf(newMedicalRecords))
                        .content(objectMapper.writeValueAsString(newMedicalRecords)))
                .andDo(print())
                .andExpect(status().isOk());


        verify(medicalRecordsController, times(1))
                .updateMedicalRecords(newMedicalRecords, firstName, lastName);
    }

    @Test
    void deleteMedicalRecords() throws Exception {
        List<String> medication = Arrays.asList("pharmacol:5000mg",
                "terazine:10mg");
        List<String> allergies = List.of("peanut");
        Date birthdate = new Date("02/06/1984");
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords = new MedicalRecords(firstName,
                lastName, birthdate, medication, allergies);

        when(medicalRecordsController.deleteMedicalRecords(newMedicalRecords, firstName, lastName))
                .thenReturn(medicalRecordsServiceImpl.deleteMedicalRecords(newMedicalRecords, firstName, lastName));

        mockMvc.perform(delete("/medicalrecord/{firstName}/{lastName}", firstName, lastName)
                        .contentType("application/json")
                        .param("newMedicalRecords", String.valueOf(newMedicalRecords))
                        .content(objectMapper.writeValueAsString(newMedicalRecords)))
                .andDo(print())
                .andExpect(status().isOk());


        verify(medicalRecordsController, times(1))
                .deleteMedicalRecords(newMedicalRecords, firstName, lastName);
    }
}