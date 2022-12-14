package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.dto.*;
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
    void getPersonCoveredByStation() {
        PersonCoveredByStationDTO personByStation = personsService.getPersonCoveredByStation("1");

        assertNotNull(personByStation);
        assertEquals(5, personByStation.getChildrenNumber());
        assertEquals(1, personByStation.getAdultNumber());
    }
    @Test
    void getPersonCoveredByStation_Unknow() {
        PersonCoveredByStationDTO personByStation = personsService.getPersonCoveredByStation("6");

        assertEquals(new PersonCoveredByStationDTO(0, 0, new ArrayList<>()), personByStation);
    }

    @Test
    void getChildrenResidenceAddress() {
        List<ChildrenResidenceAddressDTO> childrenAddress = personsService.getChildrenResidenceAddress("1509 Culver St");

        assertNotNull(childrenAddress);
        assertNotEquals(new ArrayList<>(), childrenAddress);
    }

    @Test
    void getChildrenResidenceAddress_Unknow() {
        List<ChildrenResidenceAddressDTO> childrenAddress = personsService.getChildrenResidenceAddress("1309 Culver");

        assertEquals(new ArrayList<>(), childrenAddress);
    }

    @Test
    void getResidentPhoneNumber() {
        List<String> phoneNumber = personsService.getResidentPhoneNumber("1");

        assertNotNull(phoneNumber);
        assertNotEquals(new ArrayList<>(), phoneNumber);
    }

    @Test
    void getResidentPhoneNumber_Unknow() {
        List<String> phoneNumber = personsService.getResidentPhoneNumber("6");

        assertEquals(new ArrayList<>(), phoneNumber);
    }

    @Test
    void getResidentAddressAndStationNumber() {
        List<ResidentAddressAndStationNumberDTO> addressAndStationNumber = personsService.getResidentAddressAndStationNumber("1509 Culver St");

        assertNotNull(addressAndStationNumber);
        assertNotEquals(new ArrayList<>(), addressAndStationNumber);
    }

    @Test
    void getResidentAddressAndStationNumber_Unknow() {
        List<ResidentAddressAndStationNumberDTO> addressAndStationNumber = personsService.getResidentAddressAndStationNumber("1309 Culver");

        assertEquals(new ArrayList<>(), addressAndStationNumber);
    }

    @Test
    void getHouseholdServedByStation() {
        List<HouseholdServedByStationDTO> personsServedByStation = personsService.getHouseholdServedByStation("1,2");

        assertNotNull(personsServedByStation);
        assertNotEquals(new ArrayList<>(), personsServedByStation);
    }

    @Test
    void getHouseholdServedByStation_Unknow() {
        List<HouseholdServedByStationDTO> personsServedByStation = personsService.getHouseholdServedByStation("1,2");

        assertEquals(new ArrayList<>(), personsServedByStation);
    }

    @Test
    void getInhabitantInfo() {
        List<InhabitantInfoDTO> inhabitantInfo = personsService.getInhabitantInfo("John", "Boyd");

        assertNotNull(inhabitantInfo);
        assertNotEquals(new ArrayList<>(), inhabitantInfo);
    }

    @Test
    void getInhabitantInfo_Unknow() {
        List<InhabitantInfoDTO> inhabitantInfo = personsService.getInhabitantInfo("John", "Doe");

        assertEquals(new ArrayList<>(), inhabitantInfo);
    }

    @Test
    void getEmailInhabitantOfCity() {
        List<String> email = personsService.getEmailInhabitantOfCity("Chicago");

        assertNotNull(email);
        assertNotEquals(new ArrayList<>(), email);
    }

    @Test
    void getEmailInhabitantOfCity_Unknow() {
        List<String> email = personsService.getEmailInhabitantOfCity("Chicago");

        assertEquals(new ArrayList<>(), email);
    }
}