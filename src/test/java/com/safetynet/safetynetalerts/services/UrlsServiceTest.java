package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.dto.*;
import com.safetynet.safetynetalerts.servicesImplement.UrlsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlsServiceTest {
    @Autowired
    private UrlsService urlsService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPersonCoveredByStation() {
        PersonCoveredByStationDTO personByStation = urlsService.getPersonCoveredByStation("1");

        assertNotNull(personByStation);
        assertEquals(5, personByStation.getChildrenNumber());
        assertEquals(1, personByStation.getAdultNumber());
    }
    @Test
    void getPersonCoveredByStation_UnKnow() {
        PersonCoveredByStationDTO personByStation = urlsService.getPersonCoveredByStation("6");

        assertEquals(new PersonCoveredByStationDTO(0, 0, new ArrayList<>()), personByStation);
    }

    @Test
    void getChildrenResidenceAddress() {
        List<ChildrenResidenceAddressDTO> childrenAddress = urlsService.getChildrenResidenceAddress("1509 Culver St");

        assertNotNull(childrenAddress);
    }

    @Test
    void getChildrenResidenceAddress_UnKnow() {
        List<ChildrenResidenceAddressDTO> childrenAddress = urlsService.getChildrenResidenceAddress("1309 Culver");

        assertEquals(new ArrayList<>(), childrenAddress);
    }

    @Test
    void getResidentPhoneNumber() {
        List<String> phoneNumber = urlsService.getResidentPhoneNumber("1");

        assertNotNull(phoneNumber);
    }

    @Test
    void getResidentPhoneNumber_UnKnow() {
        List<String> phoneNumber = urlsService.getResidentPhoneNumber("6");

        assertEquals(new ArrayList<>(), phoneNumber);
    }

    @Test
    void getResidentAddressAndStationNumber() {
        List<ResidentAddressAndStationNumberDTO> addressAndStationNumber = urlsService.getResidentAddressAndStationNumber("1509 Culver St");

        assertNotNull(addressAndStationNumber);
        assertNotEquals(new ArrayList<>(), addressAndStationNumber);
    }

    @Test
    void getResidentAddressAndStationNumber_UnKnow() {
        List<ResidentAddressAndStationNumberDTO> addressAndStationNumber = urlsService.getResidentAddressAndStationNumber("1309 Culver");

        assertEquals(new ArrayList<>(), addressAndStationNumber);
    }

    @Test
    void getHouseholdServedByStation() {
        List<HouseholdServedByStationDTO> personsServedByStation = urlsService.getHouseholdServedByStation("1,2,3");

        assertNotNull(personsServedByStation);
        assertNotEquals(new ArrayList<>(), personsServedByStation);
    }

    @Test
    void getHouseholdServedByStation_UnKnow() {
        List<HouseholdServedByStationDTO> personsServedByStation = urlsService.getHouseholdServedByStation("5,6");

        assertEquals(new ArrayList<>(), personsServedByStation);
    }

    @Test
    void getInhabitantInfo() {
        List<InhabitantInfoDTO> inhabitantInfo = urlsService.getInhabitantInfo("John", "Boyd");

        assertNotNull(inhabitantInfo);
        assertNotEquals(new ArrayList<>(), inhabitantInfo);
    }

    @Test
    void getInhabitantInfo_UnKnow() {
        List<InhabitantInfoDTO> inhabitantInfo = urlsService.getInhabitantInfo("John", "Doe");

        assertEquals(new ArrayList<>(), inhabitantInfo);
    }

    @Test
    void getEmailInhabitantOfCity() {
        List<String> email = urlsService.getEmailInhabitantOfCity("Culver");

        assertNotNull(email);
        assertNotEquals(new ArrayList<>(), email);
    }

    @Test
    void getEmailInhabitantOfCity_UnKnow() {
        List<String> email = urlsService.getEmailInhabitantOfCity("Chicago");

        assertEquals(new ArrayList<>(), email);
    }
}