package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.servicesImplement.FirestationsServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firestations")
@AllArgsConstructor
public class FirestationsController {
    private static final Logger logger = LogManager.getLogger("FirestationsController");
    @Autowired
    private FirestationsServiceImpl firestationsServiceImpl;

    public Firestations addFirestations(@RequestParam Firestations newFirestations) {
        return firestationsServiceImpl.addFirestations(newFirestations);
    }
}
