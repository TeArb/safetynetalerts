package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.models.Persons;

import java.util.List;

/**
 * Contains abstract method get/post/put/delete of persons service.
 *
 * @author Terry
 */
public interface IPersonsService {
    List<Persons> getPersons();
    List<Persons> addPersons(Persons newPersons);
    List<Persons> updatePersons(Persons newPersons, String firstName, String lastName);
    String deletePersons(Persons removePersons, String firstName, String lastName);
}
