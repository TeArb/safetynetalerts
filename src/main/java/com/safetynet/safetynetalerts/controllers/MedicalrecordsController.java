package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.servicesImplement.FirestationsServiceImpl;
import com.safetynet.safetynetalerts.servicesImplement.MedicalrecordsServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Contains method to get/put/post/delete the medical records.
 *
 * @author Terry
 */
@RestController
@RequestMapping("/medicalrecords")
@AllArgsConstructor
public class MedicalrecordsController {
    private static final Logger logger = LogManager.getLogger("MedicalrecordsController");
/*
    @Autowired
    private MedicalrecordsServiceImpl medicalrecordsServiceImpl;
    /**
     * Method to get a medical records.
     *
     */

}
