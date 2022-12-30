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
public class PersonsService {
    private static final Logger logger = LogManager.getLogger("PersonsService");
    @Autowired
    private FirestationsRepository firestationsRepository;
    @Autowired
    private PersonsRepository personsRepository;
    private int childrenNumber;
    private int adultNumber;

    public PersonAdultAndChildrenDTO getPersonsByStationNumber(String stationNumber) {
        List<Persons> personsList = this.personsRepository.getPersons();
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations().stream()
                .filter(firestation -> firestation.getStation().equals(stationNumber)).toList();
        List<Persons> resultPersons = new ArrayList<>();
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
                            resultPersons.add(person);
                            }
            }));
        } else {
            logger.error("Person by station number is empty");
        }
        return new PersonAdultAndChildrenDTO(adultNumber, childrenNumber, resultPersons);
    }

    public List<PersonChildrenDTO> getChildrensByAddress(String address) {
        List<Persons> personsList = this.personsRepository.getPersons().stream()
                .filter(person -> person.getAddress().equals(address) && person.getAge() <= 18).toList();
        List<PersonChildrenDTO> resultChildrens = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(
                    person -> {
                        List<Persons> householdMembers = this.personsRepository.getPersons().stream()
                                .filter(household -> household.getLastname().equals(person.getLastname())
                                        && !household.getFirstname().equals(person.getFirstname())
                        ).collect(toList());
                        resultChildrens.add(new PersonChildrenDTO(person.getFirstname(), person.getLastname(),
                                person.getAge(), householdMembers));
                    });
        } else {
            logger.error("Childrens by address is empty");
        }
        return resultChildrens;
    }

    public List<String> getPhoneNumbersByFirestation(String firestationNumber) {
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
            logger.error("Phone number by firestation is empty");
        }
        return resultPhoneNumbers;
    }

    public List<PersonsAndStationDTO> getPersonsAndStationByAddress(String address) {
        List<Persons> personsList = this.personsRepository.getPersons().stream()
                .filter(person -> person.getAddress().equals(address)).toList();
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations().stream()
                .filter(station -> station.getAddress().equals(address)).toList();
        List<PersonsAndStationDTO> resultPersonsAndStation = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(
                    person -> firestationsList.forEach(
                            station -> resultPersonsAndStation.add(
                                    new PersonsAndStationDTO(person.getLastname(), person.getPhone(), person.getAge(),
                                            person.getMedicalrecords().getMedication(),
                                            person.getMedicalrecords().getAllergies(),
                                            station.getStation())
                            )));
        } else {
            logger.error("Persons and station by address is empty");
        }
        return resultPersonsAndStation;
    }

    public List<PersonsServedByStationDTO> getFirestationList(List<String> stations) {
        List<Persons> personsList = this.personsRepository.getPersons();
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations();
        List<PersonsServedByStationDTO> resultPersonsServedByStation = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(
                    person -> firestationsList.forEach(
                            station -> {
                                String stationStr = station.getStation();

                                if (person.getAddress().equals(station.getAddress())
                                && stations.contains(stationStr)) {

                                    resultPersonsServedByStation.add(new PersonsServedByStationDTO(person.getLastname(),
                                            person.getPhone(), person.getAge(), station.getStation(),
                                            person.getMedicalrecords().getMedication(),
                                            person.getMedicalrecords().getAllergies()));
                                }
                            }));
        } else {
            logger.error("Persons served by station by address is empty");
        }
        return resultPersonsServedByStation;
    }

    public List<PersonInfoDTO> getPersonFirstNameLastName(String firstName, String lastName) {
        List<Persons> personsList = this.personsRepository.getPersons();
        List<PersonInfoDTO> resultPersonInfoDTO = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(person -> {
                if ((person.getFirstname().equals(firstName) && person.getLastname().equals(lastName))
                        || (!person.getFirstname().equals(firstName) && person.getLastname().equals(lastName))) {
                    resultPersonInfoDTO.add(new PersonInfoDTO(person.getLastname(), person.getAddress(),
                            person.getAge(), person.getEmail(), person.getMedicalrecords().getMedication(),
                            person.getMedicalrecords().getAllergies()));
                }
            });
        } else {
            logger.error("Persons info is empty");
        }
        return resultPersonInfoDTO;
    }

    public List<String> getEmailOfInhabitant(String city) {
        List<Persons> personsList = this.personsRepository.getPersons().stream()
                .filter(station -> station.getCity().equals(city)).toList();
        List<String> resultEmails = new ArrayList<>();

        if (!personsList.isEmpty()) {
            personsList.forEach(
                    person -> resultEmails.add(person.getEmail()));
        } else {
            logger.error("Email by inhabitant is empty");
        }
        return resultEmails;
    }
}