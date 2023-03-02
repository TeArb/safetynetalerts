package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonsServiceImplProvider {
    private static final Logger logger = LogManager.getLogger("PersonsServiceImplProvider");
    @Autowired
    private PersonsRepository personsRepository;
    /**
     * Get a persons list.
     *
     */
    public List<Persons> getPersons() {
        logger.info("Persons got");
        return this.personsRepository.getPersons();
    }
    /**
     * Add a person.
     *
     */
    public Persons addPersons(Persons newPersons) {
        List<Persons> personsList = personsRepository.getPersons();
        // Checks that firstName and lastName of the person is in the list.
        boolean firstName_LastNameExists = personsList.stream().anyMatch(person
                -> newPersons.getFirstName().equals(person.getFirstName())
                && newPersons.getFirstName().equals(person.getLastName()));

        // Added a non-existing person.
        if (!firstName_LastNameExists) {
            personsList.add(newPersons);
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
        List<Persons> personsList = personsRepository.getPersons();

        // Checks that firstName and lastName of the person is in the list.
        boolean firstName_LastNameExists = personsList.stream().anyMatch(person
                -> firstName.equals(person.getFirstName())
                && lastName.equals(person.getLastName()));

        // Update the person present in the list.
        if (firstName_LastNameExists) {
            // Run through the persons list to modify an existing firstName and lastName
            personsList.forEach(person -> {
                /*if (person.getFirstName().equals(firstName_LastNameExists)
                        && person.getLastName().equals(firstName_LastNameExists)) {*/
                person.setAddress(newPersons.getAddress());
                person.setCity(newPersons.getCity());
                person.setZip(newPersons.getZip());
                person.setPhone(newPersons.getPhone());
                person.setEmail(newPersons.getEmail());
            });
            logger.info("Person updated");
        } else{
            logger.error("Person firstName and lastName don't exist.");
            throw new NullPointerException("Person firstName and lastName don't exist.");
        }
        return newPersons;
    }
    /**
     * Delete a person of the list.
     *
     */
    public String deletePersons(Persons removePersons, String firstName, String lastName) {
        List<Persons> personsList = personsRepository.getPersons();
        int index = personsList.indexOf(removePersons);
        // Checks that firstName and lastName of the person is in the list.
        boolean firstName_LastNameExists = personsList.stream().anyMatch(person
                -> firstName.equals(person.getFirstName())
                && lastName.equals(person.getLastName()));

        // Remove the person present in the list.
        if (firstName_LastNameExists && (index > -1)) {
            personsList.remove(removePersons);
            logger.info("Person deleted.");
            return "Person deleted.";
        } else {
            logger.error("Person not found.");
            return "Person not found.";
        }
    }
}
