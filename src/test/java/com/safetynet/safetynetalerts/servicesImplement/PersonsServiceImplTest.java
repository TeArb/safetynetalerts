package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.repositories.PersonsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonsServiceImplTest {
    @Autowired
    protected PersonsServiceImpl personsService;
    @MockBean
    protected PersonsRepository personsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personsService = new PersonsServiceImpl(personsRepository);
    }

    @Test
    void getPersons() {
        List<Persons> personsList = new ArrayList<>();
        personsList.add(new Persons("John", "Boyd", "06/06/1984",
                "Culver", "97451", "841-874-6512", "jaboyd@email.com", new MedicalRecords()));

        when(personsRepository.getPersons()).thenReturn(personsList);

        assertEquals(1, personsService.getPersons().size());
    }

    @Test
    void addPersons() {
        Persons newPerson = new Persons("John", "Boyd", "06/06/1984",
                "Culver", "97451", "841-874-6512", "jaboyd@email.com", new MedicalRecords());

        List<Persons> personsList = personsService.addPersons(newPerson);
        personsList.add(newPerson);

        when(personsRepository.getPersons()).thenReturn(personsList);
        when(personsRepository.addPersons(newPerson)).thenReturn(newPerson);

        assertEquals(1, personsList.size());
    }

    @Test
    void updatePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        Persons newPersons = new Persons(firstName, lastName, "06/06/1984",
                "Culver", "97451", "841-874-6512", "jaboyd@email.com", new MedicalRecords());

        List<Persons> personsList = personsService
                .updatePersons(newPersons, firstName, lastName);
        personsList.add(newPersons);

        when(personsRepository.updatePersons(newPersons, firstName, lastName))
                .thenReturn(newPersons);

        assertEquals(1, personsList.size());
    }

    @Test
    void deletePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        Persons newPersons = new Persons("John", "Boyd", "06/06/1984",
                "Culver", "97451", "841-874-6512", "jaboyd@email.com", new MedicalRecords());
        String personsString = personsService
                .deletePersons(newPersons, firstName, lastName);

        assertNull(personsString);
    }
}