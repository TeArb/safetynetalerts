package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.repositories.MedicalRecordsRepositoryProvider;
import com.safetynet.safetynetalerts.services.IMedicalRecordsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class MedicalRecordsServiceImpl implements IMedicalRecordsService {
    @Autowired
    protected MedicalRecordsRepositoryProvider medicalRecordsRepositoryProvider;
    private static List<MedicalRecords> medicalRecordsList;
    /**
     * Constructor of medical records, help for the setup test.
     */
    public MedicalRecordsServiceImpl(MedicalRecordsRepositoryProvider medicalRecordsRepositoryProvider) {
        this.medicalRecordsRepositoryProvider = medicalRecordsRepositoryProvider;
    }

    /**
     * Set static the medical records list.
     */
    public void setUp() {
        medicalRecordsList = medicalRecordsRepositoryProvider.getMedicalRecords();
    }

    /**
     * Get the method to get a medical records list.
     */
    public List<MedicalRecords> getMedicalRecords() {
        setUp();
        return medicalRecordsList;
    }

    /**
     * Get the method to add a medical records list.
     */
    public List<MedicalRecords> addMedicalRecords(MedicalRecords newMedicalRecords) {
        setUp();
        medicalRecordsList.add(medicalRecordsRepositoryProvider.addMedicalRecords(newMedicalRecords));

        return medicalRecordsList;
    }

    /**
     * Get the method to update a medical records.
     */
    @Override
    public List<MedicalRecords> updateMedicalRecords(MedicalRecords newMedicalRecords, String firstName, String lastName) {
        setUp();
        medicalRecordsList.remove(medicalRecordsList.stream()
                .filter(medicalRecords-> medicalRecords.getFirstName().equals(firstName)
                        && medicalRecords.getLastName().equals(lastName))
                .findFirst().orElse(null));
        medicalRecordsList.add(this.medicalRecordsRepositoryProvider
                .updateMedicalRecords(newMedicalRecords, firstName, lastName));

        return medicalRecordsList;
    }

    /**
     * Get the method to delete a medical records.
     */
    @Override
    public String deleteMedicalRecords(MedicalRecords removeMedicalRecords, String firstName, String lastName) {
        return this.medicalRecordsRepositoryProvider.deleteMedicalRecords(removeMedicalRecords, firstName, lastName);
    }
}