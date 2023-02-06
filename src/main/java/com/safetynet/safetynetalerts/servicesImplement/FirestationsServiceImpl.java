package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.repositories.FirestationsServiceImplProvider;
import com.safetynet.safetynetalerts.services.IFirestationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationsServiceImpl implements IFirestationsService  {
    @Autowired
    private FirestationsServiceImplProvider firestationsServiceImplProvider;
    private static List<Firestations> firestationsList;

    public void setUp() {
        firestationsList = firestationsServiceImplProvider.getFirestations();
    }

    public List<Firestations> getFirestations() {
        setUp();
        return firestationsList;
    }

    public List<Firestations> addFirestations(Firestations newFirestations) {
        setUp();
        firestationsList.add(firestationsServiceImplProvider.addFirestations(newFirestations));

        return firestationsList;
    }

    @Override
    public List<Firestations> updateFirestations(Firestations newFirestations, String address) {
        setUp();
        firestationsList.remove(firestationsList.stream().filter(firestations
                -> firestations.getAddress().equals(address)).findFirst().orElse(null));
        firestationsList.add(this.firestationsServiceImplProvider.updateFirestations(newFirestations, address));

        return firestationsList;
    }

    @Override
    public String deleteFirestations(Firestations removeFirestations) {
        return this.firestationsServiceImplProvider.deleteFirestations(removeFirestations);
    }
}
