package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.repositories.MedicalRecordsRepository;
import com.safetynet.safetynetalerts.services.IMedicalRecordsService;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class MedicalRecordsServiceImpl implements IMedicalRecordsService {
    @Autowired
    protected MedicalRecordsRepository medicalRecordsRepository;
    /**
     * Constructor of medical records, help for the setup test.
     */
    public MedicalRecordsServiceImpl(MedicalRecordsRepository medicalRecordsRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
    }
    /**
     * Get the method to get a medical records list.
     */
    public List<MedicalRecords> getMedicalRecords() {
        return this.medicalRecordsRepository.getMedicalRecords();
    }
    /**
     * Get the method to add a medical records list.
     */
    public List<MedicalRecords> addMedicalRecords(@NotNull MedicalRecords newMedicalRecords) {
        medicalRecordsRepository.addMedicalRecords(newMedicalRecords);
        return this.medicalRecordsRepository.getMedicalRecords();
    }
    /**
     * Get the method to update a medical records.
     */
    @Override
    public List<MedicalRecords> updateMedicalRecords(MedicalRecords newMedicalRecords, String firstName, String lastName) {
        medicalRecordsRepository.updateMedicalRecords(newMedicalRecords, firstName, lastName);
        return this.medicalRecordsRepository.getMedicalRecords();
    }
    /**
     * Get the method to delete a medical records.
     */
    @Override
    public String deleteMedicalRecords(MedicalRecords removeMedicalRecords, String firstName, String lastName) {
        return this.medicalRecordsRepository.deleteMedicalRecords(removeMedicalRecords, firstName, lastName);
    }
}