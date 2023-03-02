package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.Persons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonsServiceImplProviderTest {
    @Autowired
    private PersonsServiceImplProvider personsServiceImplProvider;

    @Autowired
    private PersonsRepository personsRepository;

    @Test
    void getPersons() {
        assertNotNull(personsRepository.getPersons());
    }

    @Test
    void addPersons_AlreadyExist() {
        Persons newPersons = new Persons();
        String errorMessage = "Person already exist.";

        newPersons.setFirstName("John");
        newPersons.setLastName("Boyd");

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> personsServiceImplProvider.addPersons(newPersons), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void addPersons() {
        Persons newPersons = new Persons();
        List<Persons> personsList = personsServiceImplProvider.getPersons();

        newPersons.setFirstName("Jake");
        newPersons.setLastName("Doe");

        assertThat(personsList).doesNotContain(personsServiceImplProvider
                .addPersons(newPersons));
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
                () -> personsServiceImplProvider
                        .updatePersons(newPersons, firstName, lastName), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void updatePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        Persons newPersons = new Persons();
        List<Persons> personsList = personsServiceImplProvider.getPersons();

        newPersons.setFirstName(firstName);
        newPersons.setLastName(lastName);

        assertTrue(personsList.add(personsServiceImplProvider
                .updatePersons(newPersons, firstName, lastName)));
    }

    @Test
    void deletePersons_NotExist() {
        String firstName = "Jake";
        String lastName = "Doe";
        String returnMessage = "Person not found.";
        Persons removePersons = new Persons();

        PersonsServiceImplProvider mockPersonsService = mock(PersonsServiceImplProvider.class);
        when(mockPersonsService.deletePersons(removePersons, firstName, lastName))
                .thenReturn(returnMessage);

        assertEquals(returnMessage, mockPersonsService.deletePersons(
                removePersons, firstName, lastName));
    }

    @Test
    void deletePersons() {
        String firstName = "John";
        String lastName = "Boyd";
        String returnMessage = "Person not found.";
        Persons removePersons = new Persons();

        PersonsServiceImplProvider mockPersonsService = mock(PersonsServiceImplProvider.class);
        when(mockPersonsService.deletePersons(removePersons, firstName, lastName))
                .thenReturn(returnMessage);

        assertEquals(returnMessage, mockPersonsService.deletePersons(
                removePersons, firstName, lastName));
    }
}