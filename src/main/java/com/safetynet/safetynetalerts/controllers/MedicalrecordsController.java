package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.models.Medicalrecords;
import com.safetynet.safetynetalerts.servicesImplement.MedicalrecordsServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contains method to get/put/post/delete the medical records.
 *
 * @author Terry
 */
@RestController
@RequestMapping("/medicalrecord")
@AllArgsConstructor
public class MedicalrecordsController {
    private static final Logger logger = LogManager.getLogger("MedicalrecordsController");
    @Autowired
    private MedicalrecordsServiceImpl medicalrecordsServiceImpl;
    /**
     * Method to get a medical records.
     *
     */
    @GetMapping("/")
    public List<Medicalrecords> getMedicalrecords() {
        return medicalrecordsServiceImpl.getMedicalrecords();
    }
    /**
     * Method to post a medical records.
     *
     */
    @PostMapping("/")
    public List<Medicalrecords> addMedicalrecords(@RequestBody Medicalrecords newMedicalrecords) {
        return medicalrecordsServiceImpl.addMedicalrecords(newMedicalrecords);
    }
    /**
     * Method to put a medical records.
     *
     */
    @PutMapping("/{firstName}/{lastName}")
    public List<Medicalrecords> updateMedicalrecords(@RequestBody @NotNull Medicalrecords newMedicalrecords,
                                                     @PathVariable("firstName") String firstName,
                                                     @PathVariable("lastName") String lastName) {
        return medicalrecordsServiceImpl.updateMedicalrecords(newMedicalrecords, firstName, lastName);
    }
    /**
     * Method to delete a medical records.
     *
     */
    @DeleteMapping("/{firstName}/{lastName}")
    public String deleteMedicalrecords(@RequestBody Medicalrecords removeMedicalrecords,
                                       @PathVariable("firstName") String firstName,
                                       @PathVariable("lastName") String lastName) {
        return medicalrecordsServiceImpl.deleteMedicalrecords(removeMedicalrecords, firstName, lastName);
    }
}