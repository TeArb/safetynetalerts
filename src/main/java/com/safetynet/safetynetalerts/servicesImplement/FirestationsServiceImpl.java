package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.repositories.IFirestationsRepository;
import com.safetynet.safetynetalerts.services.IFirestationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationsServiceImpl implements IFirestationsService  {
    @Autowired
    private IFirestationsRepository iFirestationsRepository;

    public List<Firestations> getFirestations() {
        return iFirestationsRepository.getFirestations();
    }

    public Firestations addFirestations(Firestations newFirestations) {
        return this.iFirestationsRepository.addFirestations(newFirestations);
    }

    @Override
    public Firestations updateFirestations(Firestations oldFirestations, Firestations newFirestations) {
        return this.iFirestationsRepository.updateFirestations(oldFirestations, newFirestations);
    }

    @Override
    public String deleteFirestations(Firestations removeFirestations) {
        return this.iFirestationsRepository.deleteFirestations(removeFirestations);
    }
}
