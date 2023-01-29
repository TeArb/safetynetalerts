package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.dto.OldAndNewFirestationsDTO;
import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.servicesImplement.FirestationsServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@RequestMapping("/firestations")
@AllArgsConstructor
public class FirestationsController {
    private static final Logger logger = LogManager.getLogger("FirestationsController");
    @Autowired
    private FirestationsServiceImpl firestationsServiceImpl;
    /**
     * Method to get a fire station.
     *
     */
    @GetMapping("/")
    public List<Firestations> getFirestations() {
        return firestationsServiceImpl.getFirestations();
    }
    /**
     * Method to post a fire station.
     *
     */
    @PostMapping("/")
    public Firestations addFirestations(@RequestBody Firestations newFirestations) {
        return firestationsServiceImpl.addFirestations(newFirestations);
    }
    /**
     * Method to put a fire station.
     *
     */
    @PutMapping("/")
    public Firestations updateFirestations(@RequestBody @NotNull OldAndNewFirestationsDTO oldNewFirestations) {
        return firestationsServiceImpl.updateFirestations(oldNewFirestations.getOldFirestations(),
                oldNewFirestations.getNewFirestations());
    }
    /**
     * Method to delete a fire station.
     *
     */
    @DeleteMapping("/")
    public String deleteFirestations(@RequestBody Firestations removeFirestations) {
        return firestationsServiceImpl.deleteFirestations(removeFirestations);
    }
}
