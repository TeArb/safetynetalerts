package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.repositories.FirestationsServiceImplProvider;
import com.safetynet.safetynetalerts.services.IFirestationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationsServiceImpl implements IFirestationsService {
    @Autowired
    private FirestationsServiceImplProvider firestationsServiceImplProvider;
    private static List<Firestations> firestationsList;

    /**
     * Set static the fire station list.
     */
    public void setUp() {
            firestationsList = firestationsServiceImplProvider.getFirestations();
    }

    /**
     * Get the method to get a fire station list.
     */
    public List<Firestations> getFirestations() {
        setUp();
        return firestationsList;
    }

    /**
     * Get the method to add a fire station list.
     */
    public List<Firestations> addFirestations(Firestations newFirestations) {
        setUp();
        firestationsList.add(firestationsServiceImplProvider.addFirestations(newFirestations));

        return firestationsList;
    }

    /**
     * Get the method to update a fire station.
     */
    @Override
    public List<Firestations> updateFirestations(Firestations newFirestations, String address) {
        setUp();
        firestationsList.remove(firestationsList.stream().filter(firestations
                -> firestations.getAddress().equals(address)).findFirst().orElse(null));
        firestationsList.add(this.firestationsServiceImplProvider.updateFirestations(newFirestations, address));

        return firestationsList;
    }

    /**
     * Get the method to delete a fire station.
     */
    @Override
    public String deleteFirestations(Firestations removeFirestations) {
        return this.firestationsServiceImplProvider.deleteFirestations(removeFirestations);
    }
}
