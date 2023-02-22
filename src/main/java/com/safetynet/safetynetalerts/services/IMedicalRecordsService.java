package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.models.MedicalRecords;

import java.util.List;

/**
 * Contains abstract method get/post/put/delete of medical records service.
 *
 * @author Terry
 */
public interface IMedicalRecordsService {
    List<MedicalRecords> getMedicalRecords();
    List<MedicalRecords> addMedicalRecords(MedicalRecords newMedicalRecords);
    List<MedicalRecords> updateMedicalRecords(MedicalRecords newMedicalRecords, String firstName, String lastName);
    String deleteMedicalRecords(MedicalRecords removeMedicalRecords, String firstName, String lastName);
}
