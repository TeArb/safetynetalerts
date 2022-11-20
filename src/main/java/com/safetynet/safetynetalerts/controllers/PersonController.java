package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dto.PersonWhoAreAdult;
import com.safetynet.safetynetalerts.services.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonsService personsService;

    @RequestMapping("/firestation")
    public PersonWhoAreAdult getPersonByStations(@RequestParam String stationNumber) {
        return personsService.getPersonByStationNumber(stationNumber);
    }

}
