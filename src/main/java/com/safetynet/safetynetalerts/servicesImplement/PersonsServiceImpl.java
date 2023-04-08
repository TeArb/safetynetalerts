package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.repositories.PersonsRepository;
import com.safetynet.safetynetalerts.services.IPersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsServiceImpl implements IPersonsService {
    @Autowired
    private PersonsRepository personsRepository;
    /**
     * Constructor of persons, help for the setup test.
     */
    public PersonsServiceImpl(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }
    /**
     * Get the method to get a persons list.
     */
    public List<Persons> getPersons() {
        return this.personsRepository.getPersons();
    }

    /**
     * Get the method to add a persons list.
     */
    public List<Persons> addPersons(Persons newPersons) {
        personsRepository.addPersons(newPersons);
        return this.personsRepository.getPersons();
    }
    /**
     * Get the method to update a person.
     */
    @Override
    public List<Persons> updatePersons(Persons newPersons, String firstName, String lastName) {
        personsRepository.updatePersons(newPersons, firstName, lastName);
        return this.personsRepository.getPersons();
    }

    /**
     * Get the method to delete a person.
     */
    @Override
    public String deletePersons(Persons removePerson, String firstName, String lastName) {
        return this.personsRepository.deletePersons(removePerson, firstName, lastName);
    }
}
