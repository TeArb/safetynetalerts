package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.FireStations;
import com.safetynet.safetynetalerts.repositories.FireStationsRepositoryProvider;
import com.safetynet.safetynetalerts.services.IFireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationsServiceImpl implements IFireStationsService {
    @Autowired
    private FireStationsRepositoryProvider fireStationsRepositoryProvider;
    private static List<FireStations> fireStationsList;
    /**
     * Constructor of fire stations, help for the setup test.
     */
    public FireStationsServiceImpl(FireStationsRepositoryProvider fireStationsRepositoryProvider) {
        this.fireStationsRepositoryProvider = fireStationsRepositoryProvider;
    }

    /**
     * Set static the fire station list.
     */
    public void setUp() {
            fireStationsList = fireStationsRepositoryProvider.getFireStations();
    }

    /**
     * Get the method to get a fire station list.
     */
    public List<FireStations> getFireStations() {
        setUp();
        return fireStationsList;
    }

    /**
     * Get the method to add a fire station list.
     */
    public List<FireStations> addFireStations(FireStations newFireStations) {
        setUp();
        fireStationsList.add(fireStationsRepositoryProvider.addFireStations(newFireStations));

        return fireStationsList;
    }

    /**
     * Get the method to update a fire station.
     */
    @Override
    public List<FireStations> updateFireStations(FireStations newFireStations, String address) {
        setUp();
        fireStationsList.remove(fireStationsList.stream().filter(fireStations
                -> fireStations.getAddress().equals(address)).findFirst().orElse(null));
        fireStationsList.add(this.fireStationsRepositoryProvider.updateFireStations(newFireStations, address));

        return fireStationsList;
    }

    /**
     * Get the method to delete a fire station.
     */
    @Override
    public String deleteFireStations(FireStations removeFireStations) {
        return this.fireStationsRepositoryProvider.deleteFireStations(removeFireStations);
    }
}
