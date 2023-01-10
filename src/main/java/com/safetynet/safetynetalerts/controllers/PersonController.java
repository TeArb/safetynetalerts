package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dto.*;
import com.safetynet.safetynetalerts.services.PersonsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    private static final Logger logger = LogManager.getLogger("PersonController");
    @Autowired
    private PersonsService personsService;

    @RequestMapping("/firestation")
    public PersonCoveredByStationDTO getStationNumber(@RequestParam String stationNumber) {
        return personsService.getPersonCoveredByStation(stationNumber);
    }

    @RequestMapping("/childAlert")
        public List<ChildrenResidenceAddressDTO> getChildrensAddress(@RequestParam String address) {
        return personsService.getChildrenResidenceAddress(address);
    }

    @RequestMapping("/phoneAlert")
    public List<String> getFirestationNumber(@RequestParam String firestation) {
        return personsService.getResidentPhoneNumber(firestation);
    }

    @RequestMapping("/fire")
    public List<ResidentAddressAndStationNumberDTO> getResidentAddress(@RequestParam String address) {
        return personsService.getResidentAddressAndStationNumber(address);
    }

    @RequestMapping("/flood/stations")
    public List<HouseholdServedByStationDTO> getListOfStationNumber(@RequestParam String stations) {
        return personsService.getHouseholdServedByStation(stations);
    }

    @RequestMapping("/personInfo")
    public List<InhabitantInfoDTO> getPersonFirstNameLastName(@RequestParam String firstName, String lastName) {

        return personsService.getInhabitantInfo(firstName, lastName);
    }

    @RequestMapping("/communityEmail")
    public List<String> getCity(@RequestParam String city) {
        return personsService.getEmailInhabitantOfCity(city);
    }
}
