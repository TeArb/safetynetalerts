package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.models.Persons;
import com.safetynet.safetynetalerts.servicesImplement.PersonsServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contains method to get/put/post/delete the persons.
 *
 * @author Terry
 */
@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonsController {
    private static final Logger logger = LogManager.getLogger("PersonsController");
    @Autowired
    private PersonsServiceImpl personsServiceImpl;
    /**
     * Method to get a persons.
     *
     */
    @GetMapping("/")
    public List<Persons> getPersons() {
        return personsServiceImpl.getPersons();
    }
    /**
     * Method to post a person.
     *
     */
    @PostMapping("/")
    public List<Persons> addPersons(@RequestBody Persons newPersons) {
        return personsServiceImpl.addPersons(newPersons);
    }
    /**
     * Method to put a person.
     *
     */
    @PutMapping("/{firstName}/{lastName}")
    public List<Persons> updatePersons(@RequestBody @NotNull Persons newPersons,
                                                     @PathVariable("firstName") String firstName,
                                                     @PathVariable("lastName") String lastName) {
        return personsServiceImpl.updatePersons(newPersons, firstName, lastName);
    }
    /**
     * Method to delete a person.
     *
     */
    @DeleteMapping("/{firstName}/{lastName}")
    public String deletePersons(@RequestBody Persons removePersons,
                                       @PathVariable("firstName") String firstName,
                                       @PathVariable("lastName") String lastName) {
        return personsServiceImpl.deletePersons(removePersons, firstName, lastName);
    }
}