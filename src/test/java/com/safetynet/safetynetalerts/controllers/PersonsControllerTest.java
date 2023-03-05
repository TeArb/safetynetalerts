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
class PersonsControllerTest {
    @Autowired
    private PersonsController personsController;
    @MockBean
    protected PersonsServiceImpl  personsService;
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
        String firstName = "John";
        String lastName = "Boyd";

        List<Persons> personsList = new ArrayList<>();
        personsList.add(new Persons(firstName, lastName, "1509 Culver St", "Culver", "98000",
                "800-874-6512", "jadoe@email.com", new MedicalRecords(firstName, lastName,
                new Date("03/06/1989"), List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut"))));

        when(personsService.getPersons()).thenReturn(personsList);

        mockMvc.perform(get("/person/")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        List<Persons> persList = personsController.getPersons();
        assertEquals(persList, personsList);
    }

    @Test
    void addPersons() throws Exception {
        String firstName = "Jake";
        String lastName = "Doe";
        Persons newPersons = new Persons(firstName, lastName, "1509 Culver St", "Culver", "98000",
                "800-874-6512", "jadoe@email.com", new MedicalRecords(firstName, lastName,
                new Date("03/06/1989"), List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut")));

        List<Persons> personsList = new ArrayList<>();
        personsList.add(newPersons);

        when(personsController.addPersons(newPersons))
                .thenReturn(personsList);

        mockMvc.perform(post("/person/")
                        .contentType("application/json")
                        .param("newPersons", String.valueOf(newPersons))
                        .content(objectMapper.writeValueAsString(newPersons)))
                .andDo(print())
                .andExpect(status().isOk());

        List<Persons> persList = personsController.addPersons(newPersons);
        assertEquals(persList, personsList);
    }

    @Test
    void updatePersons() throws Exception {
        String firstName = "John";
        String lastName = "Boyd";
        Persons newPersons = new Persons(firstName, lastName, "1509 Culver St", "Culver", "98000",
                "800-874-6512", "jadoe@email.com", new MedicalRecords(firstName, lastName,
                new Date("03/06/1989"), List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut")));

        List<Persons> personsList = new ArrayList<>();
        personsList.add(newPersons);

        when(personsService.updatePersons(newPersons, firstName, lastName))
                .thenReturn(personsList);

        mockMvc.perform(put("/person/{firstName}/{lastName}", firstName, lastName)
                        .contentType("application/json")
                        .param("newPersons", String.valueOf(newPersons))
                        .content(objectMapper.writeValueAsString(newPersons)))
                .andDo(print())
                .andExpect(status().isOk());

        List<Persons> persList = personsController.updatePersons(newPersons, firstName, lastName);
        assertEquals(persList, personsList);
    }

    @Test
    void deletePersons() throws Exception {
        String firstName = "John";
        String lastName = "Boyd";
        Persons removePersons = new Persons(firstName, lastName, "1509 Culver St", "Culver", "98000",
                "800-874-6512", "jadoe@email.com", new MedicalRecords(firstName, lastName,
                new Date("03/06/1989"), List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut")));

        when(personsController.deletePersons(removePersons, firstName, lastName))
                .thenReturn(null);

        mockMvc.perform(delete("/person/{firstName}/{lastName}", firstName, lastName)
                        .contentType("application/json")
                        .param("newPersons", String.valueOf(removePersons))
                        .content(objectMapper.writeValueAsString(removePersons)))
                .andDo(print())
                .andExpect(status().isOk());

        String fireStationsString = personsController.deletePersons(removePersons, firstName, lastName);
        assertNull(fireStationsString);
    }
}