package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dto.*;
import com.safetynet.safetynetalerts.services.UrlsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UrlsController {
    private static final Logger logger = LogManager.getLogger("UrlsController");
    @Autowired
    private UrlsService urlsService;

    @RequestMapping("/firestation")
    public PersonCoveredByStationDTO getStationNumber(@RequestParam String stationNumber) {
        return urlsService.getPersonCoveredByStation(stationNumber);
    }

    @RequestMapping("/childAlert")
        public List<ChildrenResidenceAddressDTO> getChildrensAddress(@RequestParam String address) {
        return urlsService.getChildrenResidenceAddress(address);
    }

    @RequestMapping("/phoneAlert")
    public List<String> getFirestationNumber(@RequestParam String firestation) {
        return urlsService.getResidentPhoneNumber(firestation);
    }

    @RequestMapping("/fire")
    public List<ResidentAddressAndStationNumberDTO> getResidentAddress(@RequestParam String address) {
        return urlsService.getResidentAddressAndStationNumber(address);
    }

    @RequestMapping("/flood/stations")
    public List<HouseholdServedByStationDTO> getListOfStationNumber(@RequestParam String stations) {
        return urlsService.getHouseholdServedByStation(stations);
    }

    @RequestMapping("/personInfo")
    public List<InhabitantInfoDTO> getPersonFirstNameLastName(@RequestParam String firstName, String lastName) {
        return urlsService.getInhabitantInfo(firstName, lastName);
    }

    @RequestMapping("/communityEmail")
    public List<String> getCity(@RequestParam String city) {
        return urlsService.getEmailInhabitantOfCity(city);
    }
}
