package com.safetynet.safetynetalerts.controllers;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.servicesImplement.MedicalRecordsServiceImpl;
import lombok.AllArgsConstructor;
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
public class MedicalRecordsController {
    @Autowired
    private MedicalRecordsServiceImpl medicalRecordsServiceImpl;
    /**
     * Method to get a medical records.
     *
     */
    @GetMapping("/")
    public List<MedicalRecords> getMedicalRecords() {
        return medicalRecordsServiceImpl.getMedicalRecords();
    }
    /**
     * Method to post a medical records.
     *
     */
    @PostMapping("/")
    public List<MedicalRecords> addMedicalRecords(@RequestBody MedicalRecords newMedicalRecords) {
        return medicalRecordsServiceImpl.addMedicalRecords(newMedicalRecords);
    }
    /**
     * Method to put a medical records.
     *
     */
    @PutMapping("/{firstName}/{lastName}")
    public List<MedicalRecords> updateMedicalRecords(@RequestBody @NotNull MedicalRecords newMedicalRecords,
                                                     @PathVariable("firstName") String firstName,
                                                     @PathVariable("lastName") String lastName) {
        return medicalRecordsServiceImpl.updateMedicalRecords(newMedicalRecords, firstName, lastName);
    }
    /**
     * Method to delete a medical records.
     *
     */
    @DeleteMapping("/{firstName}/{lastName}")
    public String deleteMedicalRecords(@RequestBody MedicalRecords removeMedicalRecords,
                                       @PathVariable("firstName") String firstName,
                                       @PathVariable("lastName") String lastName) {
        return medicalRecordsServiceImpl.deleteMedicalRecords(removeMedicalRecords, firstName, lastName);
    }
}