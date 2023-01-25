package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.repositories.IFirestationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class FirestationsServiceImpl {
    private final IFirestationsRepository iFirestationsRepository;

    @Autowired
    public FirestationsServiceImpl(IFirestationsRepository iFirestationsRepository) {
        this.iFirestationsRepository = iFirestationsRepository;
    }

    @PostMapping("/firestation")
    public Firestations addFirestations(Firestations newFirestations) {
        this.iFirestationsRepository.save(newFirestations);
        return newFirestations;
    }
}
