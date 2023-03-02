package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.models.FireStations;
import com.safetynet.safetynetalerts.servicesImplement.FireStationsServiceImpl;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contains method to get/put/post/delete the fire stations.
 *
 * @author Terry
 */
@RestController
@RequestMapping("/firestation")
@AllArgsConstructor
public class FireStationsController {
    @Autowired
    private FireStationsServiceImpl fireStationsServiceImpl;
    /**
     * Method to get a fire station.
     *
     */
    @GetMapping("/")
    public List<FireStations> getFireStations() {
        return fireStationsServiceImpl.getFireStations();
    }
    /**
     * Method to post a fire station.
     *
     */
    @PostMapping("/")
    public List<FireStations> addFireStations(@RequestBody FireStations newFireStations) {
        return fireStationsServiceImpl.addFireStations(newFireStations);
    }
    /**
     * Method to put a fire station.
     *
     */
    @PutMapping("/{address}")
    public List<FireStations> updateFireStations(@RequestBody @NotNull FireStations newFireStations, @PathVariable String address) {
        return fireStationsServiceImpl.updateFireStations(newFireStations, address);
    }
    /**
     * Method to delete a fire station.
     *
     */
    @DeleteMapping("/")
    public String deleteFireStations(@RequestBody FireStations removeFireStations) {
        return fireStationsServiceImpl.deleteFireStations(removeFireStations);
    }
}
