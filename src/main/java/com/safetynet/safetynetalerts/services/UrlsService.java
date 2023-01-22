package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.dto.*;
import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.repositories.FirestationsRepository;
import com.safetynet.safetynetalerts.repositories.PersonsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UrlsService {
    private static final Logger logger = LogManager.getLogger("UrlsService");
    @Autowired
    private FirestationsRepository firestationsRepository;
    @Autowired
    private PersonsRepository personsRepository;
    private int childrenNumber;
    private int adultNumber;

    public PersonCoveredByStationDTO getPersonCoveredByStation(String stationNumber) {
        List<Persons> personsList = this.personsRepository.getPersons();
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations().stream()
                .filter(firestation -> firestation.getStation().equals(stationNumber)).toList();
        List<Persons> resultPersonStations = new ArrayList<>();
        childrenNumber = 0;
        adultNumber = 0;

        if (!firestationsList.isEmpty()) {
            firestationsList.forEach(
                    station -> personsList.forEach(
                        person -> {
                            if (person.getAddress().equals(station.getAddress())) {
                                int age = person.getAge();

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
        return new PersonCoveredByStationDTO(adultNumber, childrenNumber, resultPersonStations);
    }

    public List<ChildrenResidenceAddressDTO> getChildrenResidenceAddress(String address) {
        List<Persons> personsList = this.personsRepository.getPersons().stream()
                .filter(person -> person.getAddress().equals(address) && person.getAge() <= 18).toList();
        List<ChildrenResidenceAddressDTO> resultChildrensAddress = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(
                    person -> {
                        List<Persons> householdMembers = this.personsRepository.getPersons().stream()
                                .filter(household -> household.getLastname().equals(person.getLastname())
                                        && !household.getFirstname().equals(person.getFirstname())
                        ).collect(toList());
                        resultChildrensAddress.add(new ChildrenResidenceAddressDTO(person.getFirstname(), person.getLastname(),
                                person.getAge(), householdMembers));
                    });
        } else {
            logger.error("Children residence address is empty");
        }
        return resultChildrensAddress;
    }

    public List<String> getResidentPhoneNumber(String firestationNumber) {
        List<Persons> personsList = this.personsRepository.getPersons();
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations().stream()
                .filter(station -> station.getStation().equals(firestationNumber)).toList();
        List<String> resultPhoneNumbers = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(
                    person -> firestationsList.forEach(
                            station -> {
                                if (person.getAddress().equals(station.getAddress())) {
                                    resultPhoneNumbers.add(person.getPhone());
                                }
                            }));
        } else {
            logger.error("Resident phone number is empty");
        }
        return resultPhoneNumbers;
    }

    public List<ResidentAddressAndStationNumberDTO> getResidentAddressAndStationNumber(String address) {
        List<Persons> personsList = this.personsRepository.getPersons().stream()
                .filter(person -> person.getAddress().equals(address)).toList();
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations().stream()
                .filter(station -> station.getAddress().equals(address)).toList();
        List<ResidentAddressAndStationNumberDTO> resultResidentAndStation = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(
                    person -> firestationsList.forEach(
                            station -> resultResidentAndStation.add(
                                    new ResidentAddressAndStationNumberDTO(person.getLastname(), person.getPhone(), person.getAge(),
                                            person.getMedicalrecords().getMedication(),
                                            person.getMedicalrecords().getAllergies(),
                                            station.getStation())
                            )));
        } else {
            logger.error("Resident address and station number is empty");
        }
        return resultResidentAndStation;
    }

    public List<HouseholdServedByStationDTO> getHouseholdServedByStation(String stations) {
        String[] stationNumber = stations.split(",");
        List<Persons> personsList = this.personsRepository.getPersons();
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations();
        List<HouseholdServedByStationDTO> resultHouseholdServedByStation = new ArrayList<>();
        List<Firestations> resultHouseholdStation = new ArrayList<>();

        for (String s : stationNumber) {
            firestationsList.forEach(station -> {
                if (station.getStation().equals(s)) {
                    resultHouseholdStation.add(station);
                }
            });
        }
        if (!resultHouseholdStation.isEmpty()) {
            resultHouseholdStation.forEach(station -> personsList.forEach(person -> {
                if (person.getAddress().equals(station.getAddress())) {
                    resultHouseholdServedByStation.add(new HouseholdServedByStationDTO(person.getLastname() + " " + person.getFirstname(),
                            person.getPhone(), person.getAge(),
                            person.getMedicalrecords().getMedication(),
                            person.getMedicalrecords().getAllergies()));
                }
            }));
        } else {
            logger.error("Household served by station is empty");
        }
        return resultHouseholdServedByStation;
    }

    public List<InhabitantInfoDTO> getInhabitantInfo(String firstName, String lastName) {
        List<Persons> personsList = this.personsRepository.getPersons();
        List<InhabitantInfoDTO> resultInhabitantInfoDTO = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(person -> {
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
        return resultInhabitantInfoDTO;
    }

    public List<String> getEmailInhabitantOfCity(String city) {
        List<Persons> personsList = this.personsRepository.getPersons().stream()
                .filter(station -> station.getCity().equals(city)).toList();
        List<String> resultEmails = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(
                    person -> resultEmails.add(person.getEmail()));
        } else {
            logger.error("Email Inhabitant Of City is empty");
        }
        return resultEmails;
    }
}