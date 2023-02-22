package com.safetynet.safetynetalerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.servicesImplement.PersonsServiceImpl;
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
class PersonsControllerTest {
    @MockBean
    protected PersonsController personsController;
    @Autowired
    private PersonsServiceImpl  personsServiceImpl;
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
        mockMvc.perform(post("/person/")
                        .contentType("application/json")
                        .param("Persons", "newPersons")
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPersons() throws Exception {
        when(personsController.getPersons()).thenReturn(personsServiceImpl.getPersons());

        mockMvc.perform(get("/person/")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(personsController).getPersons();
    }

    @Test
    void addPersons() throws Exception {
        String firstName = "Jake";
        String lastName = "Doe";
        List<String> medication = Arrays.asList("pharmacol:5000mg",
                "terazine:10mg");
        List<String> allergies = List.of("peanut");
        Date birthdate = new Date("03/06/1989");
        MedicalRecords medicalRecords = new MedicalRecords(firstName,
                lastName, birthdate, medication, allergies);
        Persons newPersons = new Persons(firstName, lastName, "1509 Culver St", "Culver",
                "98000", "800-874-6512", "jadoe@email.com", medicalRecords);

        when(personsController.addPersons(newPersons))
                .thenReturn(personsServiceImpl.addPersons(newPersons));

        mockMvc.perform(post("/person/")
                        .contentType("application/json")
                        .param("newPersons", String.valueOf(newPersons))
                        .content(objectMapper.writeValueAsString(newPersons)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(personsController, times(1)).addPersons(newPersons);
    }

    @Test
    void updatePersons() throws Exception {
        String firstName = "John";
        String lastName = "Boyd";
        List<String> medication = Arrays.asList("pharmacol:5000mg",
                "terazine:10mg");
        List<String> allergies = List.of("peanut");
        Date birthdate = new Date("02/06/1984");
        MedicalRecords medicalRecords = new MedicalRecords(firstName,
                lastName, birthdate, medication, allergies);
        Persons newPersons = new Persons(firstName, lastName, "1509 Culver St", "Culver",
                "98000", "800-874-6512", "jadoe@email.com", medicalRecords);

        when(personsController.updatePersons(newPersons, firstName, lastName))
                .thenReturn(personsServiceImpl.updatePersons(newPersons, firstName, lastName));

        mockMvc.perform(put("/person/{firstName}/{lastName}", firstName, lastName)
                        .contentType("application/json")
                        .param("newPersons", String.valueOf(newPersons))
                        .content(objectMapper.writeValueAsString(newPersons)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(personsController, times(1)).updatePersons(newPersons, firstName, lastName);
    }

    @Test
    void deletePersons() throws Exception {
        String firstName = "John";
        String lastName = "Boyd";
        List<String> medication = Arrays.asList("pharmacol:5000mg",
                "terazine:10mg");
        List<String> allergies = List.of("peanut");
        Date birthdate = new Date("02/06/1984");
        MedicalRecords medicalRecords = new MedicalRecords(firstName,
                lastName, birthdate, medication, allergies);
        Persons removePersons = new Persons(firstName, lastName, "1509 Culver St", "Culver",
                "98000", "800-874-6512", "jadoe@email.com", medicalRecords);

        when(personsController.deletePersons(removePersons, firstName, lastName))
                .thenReturn(personsServiceImpl.deletePersons(removePersons, firstName, lastName));

        mockMvc.perform(delete("/person/{firstName}/{lastName}", firstName, lastName)
                        .contentType("application/json")
                        .param("newPersons", String.valueOf(removePersons))
                        .content(objectMapper.writeValueAsString(removePersons)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(personsController, times(1)).deletePersons(removePersons, firstName, lastName);
    }
}