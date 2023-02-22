package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.servicesImplement.UrlsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UrlsControllerTest {
    @MockBean
    protected UrlsController urlsController;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private UrlsService urlsService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getStationNumber() throws Exception {
        String stationNumber = "1";
        when(urlsController.getStationNumber(stationNumber))
                .thenReturn(urlsService.getPersonCoveredByStation(stationNumber));

        mockMvc.perform(get("/firestation")
                        .param("stationNumber", stationNumber)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(urlsController).getStationNumber(stationNumber);
    }

    @Test
    void getChildrenAddress() throws Exception {
        String address = "947 E. Rose Dr";
        when(urlsController.getChildrenAddress(address))
                .thenReturn(urlsService.getChildrenResidenceAddress(address));

        mockMvc.perform(get("/childAlert")
                        .param("address", address)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(urlsController).getChildrenAddress(address);
    }

    @Test
    void getFireStationNumber() throws Exception {
        String fireStation = "1";
        when(urlsController.getFireStationNumber(fireStation))
                .thenReturn(urlsService.getResidentPhoneNumber(fireStation));

        mockMvc.perform(get("/phoneAlert")
                        .param("fireStation", fireStation)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(urlsController).getFireStationNumber(fireStation);
    }

    @Test
    void getResidentAddress() throws Exception {
        String address = "947 E. Rose Dr";
        when(urlsController.getResidentAddress(address))
                .thenReturn(urlsService.getResidentAddressAndStationNumber(address));

        mockMvc.perform(get("/fire")
                        .param("address", address)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(urlsController).getResidentAddress(address);
    }

    @Test
    void getListOfStationNumber() throws Exception {
        String stations = "1";
        when(urlsController.getListOfStationNumber(stations))
                .thenReturn(urlsService.getHouseholdServedByStation(stations));

        mockMvc.perform(get("/flood/stations")
                        .param("stations", stations)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(urlsController).getListOfStationNumber(stations);
    }

    @Test
    void getPersonFirstNameLastName() throws Exception {
        String firstName = "John";
        String lastName = "Boyd";
        when(urlsController.getPersonFirstNameLastName(firstName, lastName))
                .thenReturn(urlsService.getInhabitantInfo(firstName, lastName));

        mockMvc.perform(get("/personInfo")
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(urlsController).getPersonFirstNameLastName(firstName, lastName);
    }

    @Test
    void getCity() throws Exception {
        String city = "Culver";
        when(urlsController.getCity(city))
                .thenReturn(urlsService.getEmailInhabitantOfCity(city));

        mockMvc.perform(get("/communityEmail")
                        .param("city", city)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(urlsController).getCity(city);
    }
}