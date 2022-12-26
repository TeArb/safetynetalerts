package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.dto.PersonAdultAndChildrenDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}