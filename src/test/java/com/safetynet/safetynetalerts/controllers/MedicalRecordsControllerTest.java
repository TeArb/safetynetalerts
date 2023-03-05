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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MedicalRecordsControllerTest {
    @Autowired
    private MedicalRecordsController medicalRecordsController;
    @MockBean
    protected MedicalRecordsServiceImpl medicalRecordsService;
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
        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        medicalRecordsList.add(new MedicalRecords("John", "Boyd", new Date("03/06/1989"),
                List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut")));

        when(medicalRecordsService.getMedicalRecords()).thenReturn(medicalRecordsList);

        mockMvc.perform(get("/medicalrecord/")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        List<MedicalRecords> medicalList = medicalRecordsController.getMedicalRecords();
        assertEquals(medicalList, medicalRecordsList);
    }

    @Test
    void addMedicalRecords() throws Exception {
        MedicalRecords newMedicalRecords = new MedicalRecords("Jake", "Doe",
                new Date("03/06/1989"), List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut"));

        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        medicalRecordsList.add(newMedicalRecords);

        when(medicalRecordsService.addMedicalRecords(newMedicalRecords))
                .thenReturn(medicalRecordsList);

        mockMvc.perform(post("/medicalrecord/")
                        .contentType("application/json")
                        .param("MedicalRecords", String.valueOf(newMedicalRecords))
                        .content(objectMapper.writeValueAsString(newMedicalRecords)))
                .andDo(print())
                .andExpect(status().isOk());

        List<MedicalRecords> medicalList = medicalRecordsController.addMedicalRecords(newMedicalRecords);
        assertEquals(medicalList, medicalRecordsList);
    }

    @Test
    void updateMedicalRecords() throws Exception {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords = new MedicalRecords(firstName, lastName,
                new Date("03/06/1989"), List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut"));

        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        medicalRecordsList.add(newMedicalRecords);

        when(medicalRecordsService.updateMedicalRecords(newMedicalRecords, firstName, lastName))
                .thenReturn(medicalRecordsList);

            mockMvc.perform(put("/medicalrecord/{firstName}/{lastName}", firstName, lastName)
                        .contentType("application/json")
                        .param("newMedicalRecords", String.valueOf(newMedicalRecords))
                        .content(objectMapper.writeValueAsString(newMedicalRecords)))
                .andDo(print())
                .andExpect(status().isOk());

        List<MedicalRecords> medicalList = medicalRecordsController
                .updateMedicalRecords(newMedicalRecords, firstName, lastName);
        assertEquals(medicalList, medicalRecordsList);
    }

    @Test
    void deleteMedicalRecords() throws Exception {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords removeMedicalRecords = new MedicalRecords(firstName, lastName,
                new Date("03/06/1989"), List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut"));

        when(medicalRecordsService.deleteMedicalRecords(removeMedicalRecords, firstName, lastName))
                .thenReturn(null);

        mockMvc.perform(delete("/medicalrecord/{firstName}/{lastName}", firstName, lastName)
                        .contentType("application/json")
                        .param("newMedicalRecords", String.valueOf(removeMedicalRecords))
                        .content(objectMapper.writeValueAsString(removeMedicalRecords)))
                .andDo(print())
                .andExpect(status().isOk());

        String fireStationsString = medicalRecordsController.deleteMedicalRecords(removeMedicalRecords, firstName, lastName);
        assertNull(fireStationsString);
    }
}