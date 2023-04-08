package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UrlsServiceTest {
    @Autowired
    private UrlsService urlsService;

    @Test
    void getPersonCoveredByStation_Empty_NotExist() {
        PersonCoveredByStationDTO personCoveredByStation = new PersonCoveredByStationDTO(
                0, 0, new ArrayList<>());

        assertEquals(personCoveredByStation, urlsService.getPersonCoveredByStation("150"));
    }

    @Test
    void getPersonCoveredByStation() {
        assertNotNull(urlsService.getPersonCoveredByStation("2"));
    }

    @Test
    void getChildrenResidenceAddress_Empty_NotExist() {
        List<ChildrenResidenceAddressDTO> personCoveredByStation = new ArrayList<>();

        assertEquals(personCoveredByStation, urlsService.getChildrenResidenceAddress("150 Bubble St"));
    }


    @Test
    void getChildrenResidenceAddress() {
        assertNotNull(urlsService.getChildrenResidenceAddress("1509 Culver St"));
    }

    @Test
    void getResidentPhoneNumber_Empty_NotExist() {
        List<String> phoneNumber = new ArrayList<>();

        assertEquals(phoneNumber, urlsService.getResidentPhoneNumber("150"));
    }

    @Test
    void getResidentPhoneNumber() {
        assertNotNull(urlsService.getResidentPhoneNumber("1"));
    }

    @Test
    void getResidentAddressAndStationNumber_Empty_NotExist() {
        List<ResidentAddressAndStationNumberDTO> addressAndStationNumber = new ArrayList<>();

        assertEquals(addressAndStationNumber, urlsService.getResidentAddressAndStationNumber("150 Bubble St"));
    }

    @Test
    void getResidentAddressAndStationNumber() {
        assertNotNull(urlsService.getResidentAddressAndStationNumber("1509 Culver St"));
    }

    @Test
    void getHouseholdServedByStation_Empty_NotExist() {
        List<HouseholdServedByStationDTO> personsServedByStation = new ArrayList<>();

        assertEquals(personsServedByStation, urlsService.getHouseholdServedByStation("5,6"));
    }

    @Test
    void getHouseholdServedByStation() {
        assertNotNull(urlsService.getHouseholdServedByStation("1,2"));
    }

    @Test
    void getInhabitantInfo_Empty_NotExist() {
        List<InhabitantInfoDTO> inhabitantInfo = new ArrayList<>();

        assertEquals(inhabitantInfo, urlsService.getInhabitantInfo("John", "Doe"));
    }

    @Test
    void getInhabitantInfo() {
        List<InhabitantInfoDTO> inhabitantInfo = urlsService.getInhabitantInfo("John", "Boyd");

        assertNotNull(inhabitantInfo);
    }

    @Test
    void getEmailInhabitantOfCity_Empty_NotExist() {
        List<String> email = new ArrayList<>();

        assertEquals(email, urlsService.getEmailInhabitantOfCity("Chicago"));
    }

    @Test
    void getEmailInhabitantOfCity() {
        assertNotNull(urlsService.getEmailInhabitantOfCity("Culver"));
    }

}