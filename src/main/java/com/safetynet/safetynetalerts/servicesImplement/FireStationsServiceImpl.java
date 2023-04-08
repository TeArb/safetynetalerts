package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.FireStations;
import com.safetynet.safetynetalerts.repositories.FireStationsRepository;
import com.safetynet.safetynetalerts.services.IFireStationsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationsServiceImpl implements IFireStationsService {
    @Autowired
    private FireStationsRepository fireStationsRepository;
    /**
     * Constructor of fire stations, help for the setup test.
     */
    public FireStationsServiceImpl(FireStationsRepository fireStationsRepository) {
        this.fireStationsRepository = fireStationsRepository;
    }
    /**
     * Get the method to get a fire station list.
     */
    public List<FireStations> getFireStations() {
        return this.fireStationsRepository.getFireStations();
    }
    /**
     * Get the method to add a fire station list.
     */
    public List<FireStations> addFireStations(@NotNull FireStations newFireStations) {
        if(fireStationsRepository.getOne(newFireStations.getAddress(), newFireStations.getStation()).isEmpty()) {
            fireStationsRepository.addFireStations(newFireStations);
        }
        return this.fireStationsRepository.getFireStations();
    }
    /**
     * Get the method to update a fire station.
     */
    @Override
    public List<FireStations> updateFireStations(FireStations newFireStations, String address) {
        fireStationsRepository.updateFireStations(newFireStations, address);
        return this.fireStationsRepository.getFireStations();
    }
    /**
     * Get the method to delete a fire station.
     */
    @Override
    public String deleteFireStations(FireStations removeFireStations) {
        return this.fireStationsRepository.deleteFireStations(removeFireStations);
    }
}
