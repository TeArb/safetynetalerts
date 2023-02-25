package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.repositories.MedicalRecordsServiceImplProvider;
import com.safetynet.safetynetalerts.services.IMedicalRecordsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class MedicalRecordsServiceImpl implements IMedicalRecordsService {
    @Autowired
    protected MedicalRecordsServiceImplProvider medicalRecordsServiceImplProvider;
    private static List<MedicalRecords> medicalRecordsList;

    public MedicalRecordsServiceImpl(MedicalRecordsServiceImplProvider medicalRecordsServiceImplProvider) {
        this.medicalRecordsServiceImplProvider = medicalRecordsServiceImplProvider;
    }

    /**
     * Set static the medical records list.
     */
    public void setUp() {
        medicalRecordsList = medicalRecordsServiceImplProvider.getMedicalRecords();
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
        medicalRecordsList.add(medicalRecordsServiceImplProvider.addMedicalRecords(newMedicalRecords));

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
        medicalRecordsList.add(this.medicalRecordsServiceImplProvider
                .updateMedicalRecords(newMedicalRecords, firstName, lastName));

        return medicalRecordsList;
    }

    /**
     * Get the method to delete a medical records.
     */
    @Override
    public String deleteMedicalRecords(MedicalRecords removeMedicalRecords, String firstName, String lastName) {
        return this.medicalRecordsServiceImplProvider.deleteMedicalRecords(removeMedicalRecords, firstName, lastName);
    }
}