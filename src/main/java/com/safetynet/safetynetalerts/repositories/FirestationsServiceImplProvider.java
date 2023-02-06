package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.Firestations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationsServiceImplProvider {
    private static final Logger logger = LogManager.getLogger("FirestationsServiceImplProvider");
    @Autowired
    private FirestationsRepository firestationsRepository;

    public List<Firestations> getFirestations() {
        logger.info("Firestations got");
        return this.firestationsRepository.getFirestations();
    }

    public Firestations addFirestations(Firestations newFirestations) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        firestationsList.add(newFirestations);
        logger.info("Firestations added");

        return newFirestations;
    }

    public Firestations updateFirestations(Firestations newFirestations, String address) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        boolean addressExsits = getFirestations().stream().anyMatch(firestations
                -> address.equals(firestations.getAddress()));

        if (addressExsits) {
            firestationsList.forEach(firestation -> {
                if (firestation.getAddress().equals(address)) {
                    firestation.setStation(newFirestations.getStation());
                    firestation.setAddress(newFirestations.getAddress());
                }});
            logger.info("Firestations updated");
        } else{
            logger.info("Firestations address don't exist");
        }
        return newFirestations;
    }

    public String deleteFirestations(Firestations removeFirestations) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        int index = getFirestations().indexOf(removeFirestations);

        if (index > -1) {
            firestationsList.remove(removeFirestations);
            return "Firestations deleted";
        }
        return "Object not found";
    }
}
