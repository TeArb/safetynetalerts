package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dto.*;
import com.safetynet.safetynetalerts.models.Firestations;
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
    public PersonAdultAndChildrenDTO getPersonByStations(@RequestParam String stationNumber) {
        return personsService.getPersonsByStationNumber(stationNumber);
    }

    @RequestMapping("/childAlert")
        public List<PersonChildrenDTO> getChildrensAddress(@RequestParam String address) {
        return personsService.getChildrensByAddress(address);
    }

    @RequestMapping("/phoneAlert")
    public List<String> getPhoneNumbersByFirestation(@RequestParam String firestation) {
        return personsService.getPhoneNumbersByFirestation(firestation);
    }

    @RequestMapping("/fire")
    public List<PersonsAndStationDTO> getPersonsAndStation(@RequestParam String address) {
        return personsService.getPersonsAndStationByAddress(address);
    }

    @RequestMapping("/flood/stations")
    public List<PersonsServedByStationDTO> getPersonsServedByStation(@RequestParam List<String> stations) {
        return personsService.getFirestationList(stations);
    }

    @RequestMapping("/personInfo")
    public List<PersonInfoDTO> getPersonInfo(@RequestParam String firstName, String lastName) {

        return personsService.getPersonFirstNameLastName(firstName, lastName);
    }

    @RequestMapping("/communityEmail")
    public List<String> getCommunityEmail(@RequestParam String city) {
        return personsService.getEmailOfInhabitant(city);
    }
}
