package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.dto.PersonWhoAreAdult;
import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.models.Medicalrecords;
import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.repositories.FirestationsRepository;
import com.safetynet.safetynetalerts.repositories.MedicalrecordsRepository;
import com.safetynet.safetynetalerts.repositories.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonsService {

    @Autowired
    private FirestationsRepository firestationsRepository;
    @Autowired
    private PersonsRepository personsRepository;
    @Autowired
    private MedicalrecordsRepository medicalrecordsRepository;
    private int childrenNumbers;
    private int adultNumbers;

    public PersonWhoAreAdult getPersonByStationNumber(String stationNumber) {
        List<Persons> personsList = this.personsRepository.getPersons();
        List<Persons> result = new ArrayList<>();

        List<Medicalrecords> medicalrecordsList = this.medicalrecordsRepository.getMedicalrecords();
        List<Firestations> firestationsList = this.firestationsRepository.getFirestations().stream().filter(
                //element -> element.getStation().equals(stationNumber)).collect(Collectors.toList());
                element -> element.getStation().equals(stationNumber)).toList();

        //if (firestationsList != null ? !firestationsList.isEmpty() : false) {
        if (!firestationsList.isEmpty()) {
            firestationsList.forEach(station -> personsList.forEach(person ->  {

                if (person.getAddress().equals(station.getAddress())) {
                    Medicalrecords medicalrecords = medicalrecordsList.stream().filter(
                           element -> element.getPerson().equals(person)
                    //).findFirst().get();
                    ).findFirst().orElse(null);
                    assert medicalrecords != null;
                    Long age = medicalrecords.getAge();

                    if (age <= 18) {
                        childrenNumbers ++;
                    } else {
                        adultNumbers ++;
                    }
                    result.add(person);
                }

            }));
        }
        return new PersonWhoAreAdult(childrenNumbers, adultNumbers, result);
    }

}
