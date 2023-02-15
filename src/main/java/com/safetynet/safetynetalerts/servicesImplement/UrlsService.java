package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.dto.*;
import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.repositories.FirestationsRepository;
import com.safetynet.safetynetalerts.repositories.PersonsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
/**
 * Contains method to get the different URLs.
 *
 * @author Terry
 */
@Service
public class UrlsService {
    private static final Logger logger = LogManager.getLogger("UrlsService");
    @Autowired
    private FirestationsRepository firestationsRepository;
    @Autowired
    private PersonsRepository personsRepository;
    private int childrenNumber;
    private int adultNumber;
    /**
     * Method to get the persons covered by station number.
     *
     */
    public PersonCoveredByStationDTO getPersonCoveredByStation(String stationNumber) {
        List<Persons> personsList = this.personsRepository.getPersons();
        // Filter the fire station equals to "stationNumber".
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations().stream()
                .filter(firestation -> firestation.getStation().equals(stationNumber)).toList();
        List<Persons> resultPersonStations = new ArrayList<>();
        boolean stationNumberExist = firestationsList.stream().anyMatch(station
                -> stationNumber.equals(station.getStation()));
        childrenNumber = 0;
        adultNumber = 0;

        if (!firestationsList.isEmpty() && stationNumberExist) {
            // Run through firestationsList and personsList.
            firestationsList.forEach(
                    station -> personsList.forEach(
                        person -> {
                            // Adds person covered by the station.
                            if (person.getAddress().equals(station.getAddress())) {
                                int age = person.getAge();

                                // Count the number of children and adults.
                                if (age <= 18) {
                                    childrenNumber ++;
                                } else {
                                     adultNumber ++;
                                }
                                resultPersonStations.add(person);
                            }
            }));
        } else {
            logger.error("Person covered by station is empty");
        }

        if (!stationNumberExist) {
            logger.error("Station number don't exist");
        }
        return new PersonCoveredByStationDTO(adultNumber, childrenNumber, resultPersonStations);
    }
    /**
     * Method to get the children's residence by address.
     *
     */
    public List<ChildrenResidenceAddressDTO> getChildrenResidenceAddress(String address) {
        // Filter the children equals to "address".
        List<Persons> personsList = this.personsRepository.getPersons().stream()
                .filter(person -> person.getAddress().equals(address) && person.getAge() <= 18).toList();
        List<ChildrenResidenceAddressDTO> resultChildrensAddress = new ArrayList<>();
        boolean addressExist = personsList.stream().anyMatch(person
                -> address.equals(person.getAddress()));

        if (!personsList.isEmpty() && addressExist) {
            // Run through personsList.
            personsList.forEach(
                    person -> {
                        if (person.getAddress().equals(address)) {
                            // Adds people whose lastnames are the same as children.
                            List<Persons> householdMembers = this.personsRepository.getPersons().stream()
                                    .filter(household -> household.getLastname().equals(person.getLastname())
                                            && !household.getFirstname().equals(person.getFirstname())
                                    ).collect(toList());
                            resultChildrensAddress.add(new ChildrenResidenceAddressDTO(person.getFirstname(), person.getLastname(),
                                    person.getAge(), householdMembers));
                        }});
        } else {
            logger.error("Children residence address is empty");
        }

        if (!addressExist) {
            logger.error("Address don't exist");
        }
        return resultChildrensAddress;
    }
    /**
     * Method to get the residents phone number by fire station number.
     *
     */
    public List<String> getResidentPhoneNumber(String firestationNumber) {
        List<Persons> personsList = this.personsRepository.getPersons();
        // Filter the fire station equals to "firestationNumber".
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations().stream()
                .filter(station -> station.getStation().equals(firestationNumber)).toList();
        List<String> resultPhoneNumbers = new ArrayList<>();
        boolean firestationNumberExist = firestationsList.stream().anyMatch(firestation
                -> firestationNumber.equals(firestation.getStation()));

        if (!personsList.isEmpty() && firestationNumberExist) {
            // Run through personsList and firestationsList.
            personsList.forEach(
                    person -> firestationsList.forEach(
                            station -> {
                                // Adds people phone number whose address are the same as fire station address.
                                if (person.getAddress().equals(station.getAddress())) {
                                    resultPhoneNumbers.add(person.getPhone());
                                }
                            }));
        } else {
            logger.error("Resident phone number is empty");
        }

        if (!firestationNumberExist) {
            logger.error("Fire station number don't exist");
        }
        return resultPhoneNumbers;
    }
    /**
     * Method to get the residents address and station number by address.
     *
     */
    public List<ResidentAddressAndStationNumberDTO> getResidentAddressAndStationNumber(String address) {
        // Filter the person equals to "address".
        List<Persons> personsList = this.personsRepository.getPersons().stream()
                .filter(person -> person.getAddress().equals(address)).toList();
        // Filter the fire station equals to "address".
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations().stream()
                .filter(station -> station.getAddress().equals(address)).toList();
        List<ResidentAddressAndStationNumberDTO> resultResidentAndStation = new ArrayList<>();
        boolean addressExist = personsList.stream().anyMatch(person
                -> address.equals(person.getAddress()));

        if (!personsList.isEmpty() && addressExist) {
            // Run through personsList and firestationsList.
            personsList.forEach(
                    person -> firestationsList.forEach(
                            // Adds the data to the empty list.
                            station -> resultResidentAndStation.add(
                                    new ResidentAddressAndStationNumberDTO(person.getLastname(), person.getPhone(),
                                            person.getAge(), person.getMedicalrecords().getMedication(),
                                            person.getMedicalrecords().getAllergies(), station.getStation())
                            )));
        } else {
            logger.error("Resident address and station number is empty");
        }

        if (!addressExist) {
            logger.error("Resident address don't exist");
        }
        return resultResidentAndStation;
    }
    /**
     * Method to get the household served by fire station.
     *
     */
    public List<HouseholdServedByStationDTO> getHouseholdServedByStation(@NotNull String stations) {
        // Line break after each comma.
        String[] stationNumber = stations.split(",");
        List<Persons> personsList = this.personsRepository.getPersons();
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations();
        List<HouseholdServedByStationDTO> resultHouseholdServedByStation = new ArrayList<>();
        List<Firestations> resultHouseholdStation = new ArrayList<>();
        boolean stationsExist = firestationsList.stream().anyMatch(station
                -> stations.equals(station.getStation()));

        for (String s : stationNumber) {
            firestationsList.forEach(station -> {
                // Adds to the empty list the fire station number equals to "stations".
                if (station.getStation().equals(s)) {
                    resultHouseholdStation.add(station);
                }
            });
        }
        if (!resultHouseholdStation.isEmpty() && stationsExist) {
            // Run through "resultHouseholdStation" list and personsList.
            resultHouseholdStation.forEach(station -> personsList.forEach(person -> {
                // Add the info whose addresses of the people are equal to those of the stations.
                if (person.getAddress().equals(station.getAddress())) {
                    resultHouseholdServedByStation.add(new HouseholdServedByStationDTO(person.getLastname()
                            + " " + person.getFirstname(), person.getPhone(), person.getAge(),
                            person.getMedicalrecords().getMedication(), person.getMedicalrecords().getAllergies()));
                }
            }));
        } else {
            logger.error("Household served by station is empty");
        }

        if (!stationsExist) {
            logger.error("Some stations don't exists");
        }
        return resultHouseholdServedByStation;
    }
    /**
     * Method to get the inhabitant info by firstName and lastName.
     *
     */
    public List<InhabitantInfoDTO> getInhabitantInfo(String firstName, String lastName) {
        List<Persons> personsList = this.personsRepository.getPersons();
        List<InhabitantInfoDTO> resultInhabitantInfoDTO = new ArrayList<>();
        boolean firstNameLastNameExists = personsList.stream().anyMatch(person
                -> firstName.equals(person.getFirstname()) && lastName.equals(person.getLastname()));

        if (!personsList.isEmpty() && firstNameLastNameExists) {
            // Run through personsList.
            personsList.forEach(person -> {
                // Add the info whose firstName and lastName of the people are equal to the input.
                if ((person.getFirstname().equals(firstName) && person.getLastname().equals(lastName))
                        || (!person.getFirstname().equals(firstName) && person.getLastname().equals(lastName))) {
                    resultInhabitantInfoDTO.add(new InhabitantInfoDTO(person.getLastname(), person.getAddress(),
                            person.getAge(), person.getEmail(), person.getMedicalrecords().getMedication(),
                            person.getMedicalrecords().getAllergies()));
                }
            });
        } else {
            logger.error("InhabitantInfo is empty");
        }

        if (!firstNameLastNameExists) {
            logger.error("Firstname or lastname don't exists");
        }
        return resultInhabitantInfoDTO;
    }
    /**
     * Method to get the email inhabitant of the city by city.
     *
     */
    public List<String> getEmailInhabitantOfCity(String city) {
        List<Persons> personsList = this.personsRepository.getPersons().stream()
                .filter(station -> station.getCity().equals(city)).toList();
        List<String> resultEmails = new ArrayList<>();
        boolean cityExist = personsList.stream().anyMatch(person
                -> city.equals(person.getCity()));

        if (!personsList.isEmpty() && cityExist) {
            // Run through personsList and add email to the empty list.
            personsList.forEach(
                    person -> resultEmails.add(person.getEmail()));
        } else {
            logger.error("Email Inhabitant Of City is empty");
        }

        if (!cityExist) {
            logger.error("City don't exist");
        }
        return resultEmails;
    }
}