package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dto.*;
import com.safetynet.safetynetalerts.servicesImplement.UrlsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Contains method to handle the navigation of the different URLs.
 *
 * @author Terry
 */
@RestController
public class UrlsController {
    private static final Logger logger = LogManager.getLogger("UrlsController");
    @Autowired
    private UrlsService urlsService;
    /**
     * Method to handle the persons covered by station number.
     *
     */
    @RequestMapping("/firestation")
    public PersonCoveredByStationDTO getStationNumber(@RequestParam String stationNumber) {
        return urlsService.getPersonCoveredByStation(stationNumber);
    }
    /**
     * Method to handle the children's residence by address.
     *
     */
    @RequestMapping("/childAlert")
        public List<ChildrenResidenceAddressDTO> getChildrenAddress(@RequestParam String address) {
        return urlsService.getChildrenResidenceAddress(address);
    }
    /**
     * Method to handle the resident phone numbers by fire station.
     *
     */
    @RequestMapping("/phoneAlert")
    public List<String> getFireStationNumber(@RequestParam String fireStation) {
        return urlsService.getResidentPhoneNumber(fireStation);
    }
    /**
     * Method to handle the resident addresses and station numbers by address.
     *
     */
    @RequestMapping("/fire")
    public List<ResidentAddressAndStationNumberDTO> getResidentAddress(@RequestParam String address) {
        return urlsService.getResidentAddressAndStationNumber(address);
    }
    /**
     * Method to handle the household served by stations.
     *
     */
    @RequestMapping("/flood/stations")
    public List<HouseholdServedByStationDTO> getListOfStationNumber(@RequestParam String stations) {
        return urlsService.getHouseholdServedByStation(stations);
    }
    /**
     * Method to handle the inhabitant info's by firstName and lastName.
     *
     */
    @RequestMapping("/personInfo")
    public List<InhabitantInfoDTO> getPersonFirstNameLastName(@RequestParam String firstName, String lastName) {
        return urlsService.getInhabitantInfo(firstName, lastName);
    }
    /**
     * Method to handle the email inhabitant of city by city.
     *
     */
    @RequestMapping("/communityEmail")
    public List<String> getCity(@RequestParam String city) {
        return urlsService.getEmailInhabitantOfCity(city);
    }
}
