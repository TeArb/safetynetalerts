package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.repositories.PersonsServiceImplProvider;
import com.safetynet.safetynetalerts.services.IPersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsServiceImpl implements IPersonsService {
    @Autowired
    private PersonsServiceImplProvider personsServiceImplProvider;
    private static List<Persons> personsList;
    /**
     * Constructor of persons, help for the setup test.
     */
    public PersonsServiceImpl(PersonsServiceImplProvider personsServiceImplProvider) {
        this.personsServiceImplProvider = personsServiceImplProvider;
    }
    /**
     * Set static the persons list.
     */
    public void setUp() {
        personsList = personsServiceImplProvider.getPersons();
    }

    /**
     * Get the method to get a persons list.
     */
    public List<Persons> getPersons() {
        setUp();
        return personsList;
    }

    /**
     * Get the method to add a persons list.
     */
    public List<Persons> addPersons(Persons newPersons) {
        setUp();
        personsList.add(personsServiceImplProvider.addPersons(newPersons));

        return personsList;
    }

    /**
     * Get the method to update a person.
     */
    @Override
    public List<Persons> updatePersons(Persons newPersons, String firstName, String lastName) {
        setUp();
        personsList.remove(personsList.stream()
                .filter(person -> person.getFirstName().equals(firstName)
                        && person.getLastName().equals(lastName))
                .findFirst().orElse(null));
        personsList.add(this.personsServiceImplProvider
                .updatePersons(newPersons, firstName, lastName));

        return personsList;
    }

    /**
     * Get the method to delete a person.
     */
    @Override
    public String deletePersons(Persons removePerson, String firstName, String lastName) {
        return this.personsServiceImplProvider.deletePersons(removePerson, firstName, lastName);
    }

}
