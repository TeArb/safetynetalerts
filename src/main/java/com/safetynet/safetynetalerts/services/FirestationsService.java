package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.repositories.IFirestationsRepository;
import com.safetynet.safetynetalerts.servicesImplement.IFirestationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirestationsService implements IFirestationsService {
    @Autowired
    private IFirestationsRepository iFirestationsRepository;

    @Override
    public Firestations addFirestations(Firestations newFirestations) {
        this.iFirestationsRepository.save(newFirestations);
        return newFirestations;
    }
}
