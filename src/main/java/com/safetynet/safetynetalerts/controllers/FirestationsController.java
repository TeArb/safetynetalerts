package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.services.FirestationsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.component.annotations.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationsController {
    private static final Logger logger = LogManager.getLogger("FirestationsController");
    @Autowired
    private FirestationsService firestationsService;

    @RequestMapping("/firestation")
    public Firestations addFirestations(@RequestParam Firestations newFirestations) {
        return firestationsService.addFirestations(newFirestations);
    }
}
