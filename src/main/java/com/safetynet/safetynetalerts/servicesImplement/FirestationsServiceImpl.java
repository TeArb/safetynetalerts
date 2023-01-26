package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.repositories.IFirestationsRepository;
import com.safetynet.safetynetalerts.services.IFirestationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class FirestationsServiceImpl implements IFirestationsService  {
    @Autowired
    private IFirestationsRepository iFirestationsRepository;

    public Firestations addFirestations(Firestations newFirestations) {
        this.iFirestationsRepository.addF(newFirestations);
        //this.iFirestationsRepository.save(newFirestations);
        return newFirestations;
    }
}
