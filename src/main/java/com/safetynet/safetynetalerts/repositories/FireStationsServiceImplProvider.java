package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.FireStations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationsServiceImplProvider {
    private static final Logger logger = LogManager.getLogger("FireStationsServiceImplProvider");
    @Autowired
    private FireStationsRepository fireStationsRepository;
    /**
     * Get a fire stations list.
     *
     */
    public List<FireStations> getFireStations() {
        logger.info("Fire stations got");
        return this.fireStationsRepository.getFireStations();
    }
    /**
     * Add a fire station.
     *
     */
    public FireStations addFireStations(@NotNull FireStations newFireStations) {
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        boolean fireStationsExists = fireStationsList.stream().anyMatch(newFireStations::equals);

        // Added a non-existing fire station.
        if (!fireStationsExists) {
            fireStationsList.add(newFireStations);
            logger.info("Fire stations added");
        } else {
            logger.error("Fire station already exist.");
            throw new NullPointerException("Fire station already exist.");
        }
        return newFireStations;
    }
    /**
     * Update a fire station of the list.
     *
     */
    public FireStations updateFireStations(FireStations newFireStations, String address) {
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        // Checks that address of the fire station is in the list.
        boolean addressExist = fireStationsList.stream().anyMatch(fireStation
                -> address.equals(fireStation.getAddress()));

        // Update the fire station present in the list.
        if (addressExist) {
            // Run through the fire station list to modify an existing address
            fireStationsList.forEach(fireStation -> {
                if (fireStation.getAddress().equals(address)) {
                    fireStation.setStation(newFireStations.getStation());
                    fireStation.setAddress(newFireStations.getAddress());
                }});
            logger.info("Fire stations updated");
        } else{
            logger.error("Fire stations address don't exist.");
            throw new NullPointerException("Fire stations address don't exist.");
        }
        return newFireStations;
    }
    /**
     * Delete a fire station of the list.
     *
     */
    public String deleteFireStations(FireStations removeFireStations) {
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        int index = fireStationsList.indexOf(removeFireStations);

        // Remove the fire station present in the list.
        if (index > -1) {
            fireStationsList.remove(removeFireStations);
            logger.info("Fire station deleted.");
            return "Fire station deleted.";
        } else {
            logger.error("Fire station not found.");
            return "Fire station not found.";
        }
    }
}
