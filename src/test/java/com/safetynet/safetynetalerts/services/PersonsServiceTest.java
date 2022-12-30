package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.dto.PersonAdultAndChildrenDTO;
import com.safetynet.safetynetalerts.dto.PersonChildrenDTO;
import com.safetynet.safetynetalerts.dto.PersonsAndStationDTO;
import com.safetynet.safetynetalerts.dto.PersonsServedByStationDTO;
import com.safetynet.safetynetalerts.repositories.FirestationsRepository;
import com.safetynet.safetynetalerts.repositories.PersonsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonsServiceTest {
    @Autowired
    private PersonsService personsService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPersonByStationNumber() {
        PersonAdultAndChildrenDTO personAdultAndChildrenDTO = personsService.getPersonsByStationNumber("1");
        assertNotNull(personsService.getPersonsByStationNumber("1"));
        assertEquals(5, personAdultAndChildrenDTO.getChildrenNumber());
        assertEquals(1, personAdultAndChildrenDTO.getAdultNumber());
    }
    @Test
    void getPersonByStationNumber_Unknow() {
        PersonAdultAndChildrenDTO personAdultAndChildrenDTO = personsService.getPersonsByStationNumber("6");
        assertEquals(new PersonAdultAndChildrenDTO(0, 0, new ArrayList<>()), personAdultAndChildrenDTO);
    }

    @Test
    void getChildrensByAddress() {
        assertNotNull(personsService.getChildrensByAddress("1509 Culver St"));
        assertNotEquals(new ArrayList<>(), personsService.getChildrensByAddress("1509 Culver St"));
    }

    @Test
    void getChildrensByAddress_Unknow() {
        List<PersonChildrenDTO> personChildrenDTO = personsService.getChildrensByAddress("1309 Culver");
        assertEquals(new ArrayList<>(), personChildrenDTO);
    }

    @Test
    void getPhoneNumbersByFirestation() {
        assertNotNull(personsService.getPhoneNumbersByFirestation("1"));
        assertNotEquals(new ArrayList<>(), personsService.getPhoneNumbersByFirestation("1"));
    }

    @Test
    void getPhoneNumbersByFirestation_Unknow() {
        List<String> phoneNumbersList = personsService.getPhoneNumbersByFirestation("6");
        assertEquals(new ArrayList<>(), phoneNumbersList);
    }

    @Test
    void getPersonsAndStationByAddress() {
        assertNotNull(personsService.getPersonsAndStationByAddress("1509 Culver St"));
        assertNotEquals(new ArrayList<>(), personsService.getPersonsAndStationByAddress("1509 Culver St"));
    }

    @Test
    void getPersonsAndStationByAddress_Unknow() {
        List<PersonsAndStationDTO> personsAndStationDTOS = personsService.getPersonsAndStationByAddress("1309 Culver");
        assertEquals(new ArrayList<>(), personsAndStationDTOS);
    }

    @Test
    void getFirestationList() {
        List<String> stations = new ArrayList<>();
        stations.add("1");
        stations.add("2");
        assertNotEquals(new ArrayList<>(), personsService.getFirestationList(stations));
    }

    @Test
    void getFirestationList_Unknow() {
        List<String> stations = new ArrayList<>();
        stations.add("6");
        stations.add("7");
        List<PersonsServedByStationDTO> personsServedByStationDTO = personsService.getFirestationList(stations);
        assertEquals(new ArrayList<>(), personsServedByStationDTO);
    }

    @Test
    void getPersonFirstNameLastName() {
    }

    @Test
    void getEmailOfInhabitant() {
    }
}