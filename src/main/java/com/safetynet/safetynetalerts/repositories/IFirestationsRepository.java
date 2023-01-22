package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.Firestations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFirestationsRepository extends CrudRepository<Firestations, Long> {
}
