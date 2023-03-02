package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.repositories.PersonsServiceImplProvider;
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
    protected PersonsServiceImpl personsServiceImpl;
    @MockBean
    protected PersonsServiceImplProvider personsServiceImplProvider;

    @Test
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personsServiceImpl = new PersonsServiceImpl(personsServiceImplProvider);
    }

    @Test
    void getPersons() {
        List<Persons> personsList = new ArrayList<>();
        personsList.add(new Persons("John", "Boyd", "06/06/1984",
                "Culver", "97451", "841-874-6512", "jaboyd@email.com", new MedicalRecords()));

        when(personsServiceImplProvider.getPersons()).thenReturn(personsList);

        assertEquals(1, personsServiceImpl.getPersons().size());
    }

    @Test
    void addPersons() {
        Persons newPersons = new Persons("John", "Boyd", "06/06/1984",
                "Culver", "97451", "841-874-6512", "jaboyd@email.com", new MedicalRecords());
        List<Persons> medicalRecordsList = personsServiceImpl.addPersons(newPersons);

        assertEquals(1, medicalRecordsList.size());
    }

    @Test
    void updatePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        Persons newPersons = new Persons("John", "Boyd", "06/06/1984",
                "Culver", "97451", "841-874-6512", "jaboyd@email.com", new MedicalRecords());
        List<Persons> personsList = personsServiceImpl.updatePersons(newPersons, firstName, lastName);

        assertEquals(1, personsList.size());
    }

    @Test
    void deletePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        Persons newPersons = new Persons("John", "Boyd", "06/06/1984",
                "Culver", "97451", "841-874-6512", "jaboyd@email.com", new MedicalRecords());
        String medicalRecordsString = personsServiceImpl
                .deletePersons(newPersons, firstName, lastName);

        assertNull(medicalRecordsString);
    }
}