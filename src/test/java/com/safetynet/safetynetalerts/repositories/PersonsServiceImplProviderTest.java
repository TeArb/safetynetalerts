package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.models.Persons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonsServiceImplProviderTest {
    @Autowired
    private PersonsServiceImplProvider personsProvider;

    @MockBean
    protected PersonsRepository personsRepository;

    @BeforeEach
    void setUp() {
        personsProvider = new PersonsServiceImplProvider(personsRepository);
    }


    @Test
    void getPersons() {
        assertNotNull(personsRepository.getPersons());
    }

    @Test
    void addPersons_AlreadyExist() {
        List<Persons> personsList = new ArrayList<>();
        Persons newPersons  = new Persons("Jake", "Doe", "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords());

        personsList.add(newPersons);

        when(personsRepository.getPersons()).thenReturn(personsList);

        String errorMessage = "Person already exist.";
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class,
                () -> personsProvider.addPersons(newPersons), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void addPersons() {
        Persons newPersons = new Persons();
        List<Persons> personsList = personsProvider.getPersons();

        newPersons.setFirstName("Jake");
        newPersons.setLastName("Doe");

        assertThat(personsList).doesNotContain(personsProvider.addPersons(newPersons));
    }

    @Test
    void updatePersons_NotExist() {
        String firstName = "Jake";
        String lastName = "Doe";
        Persons newPersons = new Persons();
        String errorMessage = "Person " + firstName + " " + lastName + " don't exist.";

        newPersons.setFirstName(firstName);
        newPersons.setLastName(lastName);

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> personsProvider.updatePersons(newPersons, firstName, lastName), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void updatePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        List<Persons> personsList = new ArrayList<>();
        Persons newPersons  = new Persons(firstName, lastName, "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords());

        personsList.add(newPersons);
        personsList.add(new Persons("Jake", "Doe", "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords()));

        when(personsProvider.getPersons()).thenReturn(personsList);

        assertEquals(newPersons, personsProvider.updatePersons(newPersons, firstName, lastName));
    }

    @Test
    void deletePersons_NotExist() {
        String firstName = "John";
        String lastName = "Boyd";
        List<Persons> personsList = new ArrayList<>();
        Persons removePersons  = new Persons();
        personsList.add(removePersons);
        personsList.add(new Persons("Jake", "Doe", "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords()));

        when(personsRepository.getPersons()).thenReturn(personsList);

        String personString = personsProvider.deletePersons(removePersons, firstName, lastName);
        assertEquals("Person not found.", personString);
    }

    @Test
    void deletePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        List<Persons> personsList = new ArrayList<>();
        Persons removePersons  = new Persons(firstName, lastName, "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords());

        personsList.add(removePersons);
        personsList.add(new Persons("Jake", "Doe", "1509 Culver St",
                "Culver", "98000", "800-874-6512", "jadoe@email.com", new MedicalRecords()));

        when(personsRepository.getPersons()).thenReturn(personsList);

        String personString = personsProvider.deletePersons(removePersons, firstName, lastName);
        assertEquals("Person deleted.", personString);
    }
}