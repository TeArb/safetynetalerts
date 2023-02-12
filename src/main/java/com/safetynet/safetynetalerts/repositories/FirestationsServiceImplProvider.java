package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.Firestations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationsServiceImplProvider {
    private static final Logger logger = LogManager.getLogger("FirestationsServiceImplProvider");
    @Autowired
    private FirestationsRepository firestationsRepository;
    /**
     * Get a fire station list.
     *
     */
    public List<Firestations> getFirestations() {
        logger.info("Fire stations got");
        return this.firestationsRepository.getFirestations();
    }
    /**
     * Add a fire station.
     *
     */
    public Firestations addFirestations(@NotNull Firestations newFirestations) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        boolean firestationsExsits = firestationsList.stream().anyMatch(newFirestations::equals);

        // Added a non-existing fire station.
        if (!firestationsExsits) {
            firestationsList.add(newFirestations);
            logger.info("Fire stations added");
        } else {
            logger.error("Fire stations already exist");
            throw new IllegalArgumentException("Fire stations already exist");
        }
        return newFirestations;
    }
    /**
     * Update a fire station of the list.
     *
     */
    public Firestations updateFirestations(Firestations newFirestations, String address) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        // Checks that address of the fire station is in the list.
        boolean addressExsits = firestationsList.stream().anyMatch(firestations
                -> address.equals(firestations.getAddress()));

        // Update the fire station present in the list.
        if (addressExsits) {
            // Run through the fire station list to modify an existing address
            firestationsList.forEach(firestation -> {
                if (firestation.getAddress().equals(address)) {
                    firestation.setStation(newFirestations.getStation());
                    firestation.setAddress(newFirestations.getAddress());
                }});
            logger.info("Fire stations updated");
        } else{
            logger.error("Fire stations address don't exist");
            throw new NullPointerException("Fire stations address don't exist");
        }
        return newFirestations;
    }
    /**
     * Delete a fire station of the list.
     *
     */
    public String deleteFirestations(Firestations removeFirestations) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        int index = firestationsList.indexOf(removeFirestations);

        // Remove the fire station present in the list.
        if (index > -1) {
            firestationsList.remove(removeFirestations);
            logger.info("Fire station deleted");
            return "Fire station deleted";
        } else {
            logger.error("Fire station not found");
            return "Fire station not found";
        }
    }
}
