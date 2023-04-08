package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.models.Persons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonsRepositoryTest {
    @Autowired
    private PersonsRepository personsRepository;

    @Test
    void getPersons() {
        assertNotNull(personsRepository.getPersons());
    }

    @Test
    void addPersons_AlreadyExist() {
        Persons newPerson  = new Persons("Jake", "Doe", "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords());

        List<Persons> personsList = personsRepository.getPersons();
        personsList.add(newPerson);

        String errorMessage = "Person already exist.";
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class,
                () -> personsRepository.addPersons(newPerson), errorMessage);

        personsList.add(newPerson);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void addPersons() {
        Persons newPerson = new Persons("Bill", "Wiks", "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords());

        List<Persons> personsList = personsRepository.getPersons();

        assertThat(personsList).contains(personsRepository
                .addPersons(newPerson));
    }

    @Test
    void updatePersons_NotExist() {
        String firstName = "Jake";
        String lastName = "Doe";
        String errorMessage = "Person " + firstName + " " + lastName + " don't exist.";
        Persons person  = new Persons("Dane", "Fall", "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords());

        List<Persons> personsList = personsRepository.getPersons();
        personsList.add(person);

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> personsRepository.updatePersons(person, firstName, lastName), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void updatePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        Persons person  = new Persons(firstName, lastName, "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords());

        List<Persons> personsList = personsRepository.getPersons();
        personsList.add(person);

        assertThat(personsList).contains(personsRepository
                .updatePersons(person, firstName, lastName));
    }

    @Test
    void deletePersons_NotExist() {
        String firstName = "John";
        String lastName = "Boyd";
        Persons removePerson  = new Persons("Jake", "Doe", "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords());

        List<Persons> personsList = personsRepository.getPersons();
        personsList.add(removePerson);

        String personString = personsRepository.deletePersons(removePerson, firstName, lastName);
        assertEquals("Person not found.", personString);
    }

    @Test
    void deletePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        Persons removePerson  = new Persons(firstName, lastName, "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords());

        List<Persons> personsList = personsRepository.getPersons();
        personsList.add(removePerson);

        String personString = personsRepository.deletePersons(removePerson, firstName, lastName);
        assertEquals("Person deleted.", personString);
    }
}