package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.controllers.dto.*;
import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.servicesImplement.UrlsService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UrlsControllerTest {
    @Autowired
    private UrlsController urlsController;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    protected UrlsService urlsService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getStationNumber() throws Exception {
        String stationNumber = "1";
        String firstName = "John";
        String lastName = "Boyd";

        List<Persons> personsList = new ArrayList<>();
        personsList.add(new Persons(firstName, lastName, "1509 Culver St", "Culver", "98000",
                "800-874-6512", "jadoe@email.com", new MedicalRecords(firstName, lastName,
                new Date("03/06/1989"), List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut"))));

        PersonCoveredByStationDTO personCoveredByStation =
                new PersonCoveredByStationDTO(5, 1, personsList);

        when(urlsService.getPersonCoveredByStation(stationNumber)).thenReturn(personCoveredByStation);

        mockMvc.perform(get("/firestation")
                        .param("stationNumber", stationNumber)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        PersonCoveredByStationDTO personStations = urlsController.getStationNumber(stationNumber);
        assertEquals(personStations, personCoveredByStation);
    }

    @Test
    void getChildrenAddress() throws Exception {
        String address = "947 E. Rose Dr";
        String firstName = "Jake";
        String lastName = "Doe";

        List<Persons> householdMembers = new ArrayList<>();
        householdMembers.add(new Persons(firstName, lastName, "1509 Culver St", "Culver", "98000",
                "800-874-6512", "jadoe@email.com", new MedicalRecords(firstName, lastName,
                new Date("03/06/1989"), List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut"))));

        List<ChildrenResidenceAddressDTO> childrenList = new ArrayList<>();
        childrenList.add(new ChildrenResidenceAddressDTO(firstName, lastName, 12, householdMembers));

        when(urlsService.getChildrenResidenceAddress(address)).thenReturn(childrenList);

        mockMvc.perform(get("/childAlert")
                        .param("address", address)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        List<ChildrenResidenceAddressDTO> childrenAddress = urlsController.getChildrenAddress(address);
        assertEquals(childrenAddress, childrenList);
    }

    @Test
    void getFireStationNumber() throws Exception {
        String fireStation = "1";
        List<String> phoneList = List.of("800-874-6512", "800-874-6512");

        when(urlsService.getResidentPhoneNumber(fireStation)).thenReturn(phoneList);

        mockMvc.perform(get("/phoneAlert")
                        .param("fireStation", fireStation)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        List<String> fireStationNumber = urlsController.getFireStationNumber(fireStation);
        assertEquals(fireStationNumber, phoneList);
    }

    @Test
    void getResidentAddress() throws Exception {
        String address = "947 E. Rose Dr";

        List<ResidentAddressAndStationNumberDTO> residentList = new ArrayList<>();
        residentList.add(new ResidentAddressAndStationNumberDTO("Doe", "800-874-6512", 20,
                List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut"), "1"));

        when(urlsService.getResidentAddressAndStationNumber(address)).thenReturn(residentList);

        mockMvc.perform(get("/fire")
                        .param("address", address)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        List<ResidentAddressAndStationNumberDTO> residentAddress = urlsController.getResidentAddress(address);
        assertEquals(residentAddress, residentList);
    }

    @Test
    void getListOfStationNumber() throws Exception {
        String stations = "1";

        List<HouseholdServedByStationDTO> householdList = new ArrayList<>();
        householdList.add(new HouseholdServedByStationDTO("Doe" ,"800-874-6512",
                20, List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut")));

        when(urlsService.getHouseholdServedByStation(stations)).thenReturn(householdList);

        mockMvc.perform(get("/flood/stations")
                        .param("stations", stations)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        List<HouseholdServedByStationDTO> stationNumberList = urlsController.getListOfStationNumber(stations);
        assertEquals(stationNumberList, householdList);
    }

    @Test
    void getPersonFirstNameLastName() throws Exception {
        String firstName = "John";
        String lastName = "Boyd";

        List<InhabitantInfoDTO> inhabitantInfoList = new ArrayList<>();
        inhabitantInfoList.add(new InhabitantInfoDTO("Doe", "15 St James", 25,
                "jakedoe@email.com", List.of("pharmacol:5000mg", "terazine:10mg"), List.of("peanut")));

        when(urlsService.getInhabitantInfo(firstName, lastName)).thenReturn(inhabitantInfoList);

        mockMvc.perform(get("/personInfo")
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        List<InhabitantInfoDTO> personFirstNameLastName = urlsController.getPersonFirstNameLastName(firstName, lastName);
        assertEquals(personFirstNameLastName, inhabitantInfoList);
    }

    @Test
    void getCity() throws Exception {
        String city = "Culver";
        List<String> emailsList = List.of("jakedoe@email.com","jakedoe@email.com");

        when(urlsService.getEmailInhabitantOfCity(city)).thenReturn(emailsList);

        mockMvc.perform(get("/communityEmail")
                        .param("city", city)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        List<String> cityString = urlsController.getCity(city);
        assertEquals(cityString, emailsList);
    }
}