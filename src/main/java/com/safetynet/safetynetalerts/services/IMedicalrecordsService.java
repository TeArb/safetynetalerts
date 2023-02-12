package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.models.Medicalrecords;

import java.util.List;

/**
 * Contains abstract method get/post/put/delete of medical records service.
 *
 * @author Terry
 */
public interface IMedicalrecordsService {
    List<Medicalrecords> getMedicalrecords();
    List<Medicalrecords> addMedicalrecords(Medicalrecords newMedicalrecords);
    List<Medicalrecords> updateMedicalrecords(Medicalrecords newMedicalrecords, String firstName, String lastName);
    String deleteMedicalrecords(Medicalrecords removeMedicalrecords, String firstName, String lastName);
}
