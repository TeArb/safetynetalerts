package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.constants.DataLoader;
import com.safetynet.safetynetalerts.models.FireStations;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Contains method to convert String to JSON object for fire stations.
 *
 * @author Terry
 */
@Repository
@AllArgsConstructor
public class FireStationsRepository {
    private static final Logger logger = LogManager.getLogger("FireStationsRepository");
    /**
     * Method to filter the fire stations info's from JSON file.
     * Allows to know if the station and the address are present.
     */
    public Optional<FireStations> getOne(String address, String station) {
        return DataLoader.FIRE_STATIONS_LIST.stream().filter(element ->
                element.getAddress().equals(address) && element.getStation().equals(station)).findFirst();
    }
    /**
     * Get a fire stations list.
     *
     */
    public List<FireStations> getFireStations() {
        logger.info("Fire stations got");
        return DataLoader.FIRE_STATIONS_LIST;
    }
    /**
     * Add a fire station.
     *
     */
    public FireStations addFireStations(@NotNull FireStations newFireStations) {
        // Checks that address of the fire station is in the list.
        boolean fireStationExist = DataLoader.FIRE_STATIONS_LIST.stream().anyMatch(fireStation
                -> fireStation.getAddress().equals(newFireStations.getAddress())
                && fireStation.getStation().equals(newFireStations.getStation()));

        if (!fireStationExist) {
            DataLoader.FIRE_STATIONS_LIST.add(newFireStations);
            logger.info("Fire stations added");
        } else{
            logger.error("Fire station already exist.");
            throw new IllegalArgumentException("Fire station already exist.");
        }
        return newFireStations;
    }
    /**
     * Update a fire station of the list.
     *
     */
    public FireStations updateFireStations(FireStations newFireStations, String address) {
        // Checks that address of the fire station is in the list.
        boolean addressExist = DataLoader.FIRE_STATIONS_LIST.stream().anyMatch(fireStation
                -> address.equals(fireStation.getAddress()));

        // Update the fire station present in the list.
        if (addressExist) {
            // Run through the fire station list to modify an existing address
            DataLoader.FIRE_STATIONS_LIST.forEach(fireStation -> {
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
        int index = DataLoader.FIRE_STATIONS_LIST.indexOf(removeFireStations);

        // Remove the fire station present in the list.
        if (index > -1) {
            DataLoader.FIRE_STATIONS_LIST.remove(removeFireStations);
            logger.info("Fire station deleted.");
            return "Fire station deleted.";
        } else {
            logger.error("Fire station not found.");
            return "Fire station not found.";
        }
    }
}