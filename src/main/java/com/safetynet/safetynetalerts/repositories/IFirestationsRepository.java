package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.Firestations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IFirestationsRepository {
    @Autowired
    private FirestationsRepository firestationsRepository;

    public Firestations addF (Firestations firestations) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        firestationsList.add(firestations);
        return firestations;
    }



}
