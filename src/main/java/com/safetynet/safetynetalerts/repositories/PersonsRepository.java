package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.constants.DataLoader;
import com.safetynet.safetynetalerts.models.Persons;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Method to get the persons info's from JSON file.
 *
 */
@Repository
@AllArgsConstructor
public class PersonsRepository {
    private static final Logger logger = LogManager.getLogger("PersonsRepository");

    /**
     * Get a persons list.
     *
     */
    public List<Persons> getPersons() {
        logger.info("Persons got");
        return DataLoader.PERSONS_LIST;
    }
    /**
     * Add a person.
     *
     */
    public Persons addPersons(@NotNull Persons newPersons) {
        // Checks that firstName and lastName of the person is in the list.
        boolean firstName_LastNameExists = DataLoader.PERSONS_LIST.stream().anyMatch(
                person -> newPersons.getFirstName().equals(person.getFirstName())
                && newPersons.getLastName().equals(person.getLastName()));

        // Added a non-existing person.
        if (!firstName_LastNameExists) {
            DataLoader.PERSONS_LIST.add(newPersons);
            logger.info("Person added");
        } else {
            logger.error("Person already exist.");
            throw new IllegalArgumentException("Person already exist.");
        }
        return newPersons;
    }
    /**
     * Update a person of the list.
     *
     */
    public Persons updatePersons(Persons newPersons, String firstName, String lastName) {
        // Checks that firstName and lastName of the person is in the list.
        boolean firstName_LastNameExists = DataLoader.PERSONS_LIST.stream().anyMatch(
                person -> firstName.equals(person.getFirstName())
                && lastName.equals(person.getLastName()));

        // Update the person present in the list.
        if (firstName_LastNameExists && (firstName.equals(newPersons.getFirstName())
                && lastName.equals(newPersons.getLastName()))) {
            // Run through the persons list to modify an existing firstName and lastName
            DataLoader.PERSONS_LIST.forEach(person -> {
                if (person.getFirstName().equals(firstName)
                        && person.getLastName().equals(lastName)) {
                person.setAddress(newPersons.getAddress());
                person.setCity(newPersons.getCity());
                person.setZip(newPersons.getZip());
                person.setPhone(newPersons.getPhone());
                person.setEmail(newPersons.getEmail());
                }
            });
            logger.info("Person updated");
        } else{
            logger.error("Person "  + firstName + " " + lastName + " don't exist.");
            throw new NullPointerException("Person "  + firstName + " " + lastName + " don't exist.");
        }
        return newPersons;
    }
    /**
     * Delete a person of the list.
     *
     */
    public String deletePersons(Persons removePersons, String firstName, String lastName) {
        int index = DataLoader.PERSONS_LIST.indexOf(removePersons);
        // Checks that firstName and lastName of the person is in the list.
        boolean firstName_LastNameExists = DataLoader.PERSONS_LIST.stream().anyMatch(
                person -> firstName.equals(person.getFirstName())
                && lastName.equals(person.getLastName()));

        // Remove the person present in the list.
        if (firstName_LastNameExists && (index > -1) && (firstName.equals(removePersons.getFirstName())
                && lastName.equals(removePersons.getLastName()))) {
            DataLoader.PERSONS_LIST.remove(removePersons);
            logger.info("Person deleted.");
            return "Person deleted.";
        } else {
            logger.error("Person not found.");
            return "Person not found.";
        }
    }
}