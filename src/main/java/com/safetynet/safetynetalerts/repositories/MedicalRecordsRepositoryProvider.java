package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsRepositoryProvider {
    private static final Logger logger = LogManager.getLogger("MedicalRecordsRepositoryProvider");
    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;
    /**
     * Constructor of MedicalRecordsRepositoryProvider, help for the setup test.
     */
    public MedicalRecordsRepositoryProvider(MedicalRecordsRepository medicalRecordsRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
    }

    /**
     * Get a medical records list.
     *
     */
    public List<MedicalRecords> getMedicalRecords() {
        logger.info("Medical records got");
        return this.medicalRecordsRepository.getMedicalRecords();
    }
    /**
     * Add a medical records.
     *
     */
    public MedicalRecords addMedicalRecords(MedicalRecords newMedicalRecords) {
        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();
        // Checks that firstName and lastName of the medical records is in the list.
        boolean firstName_LastNameExists = medicalRecordsList.stream().anyMatch(medicalRecords
                -> newMedicalRecords.getFirstName().equals(medicalRecords.getFirstName())
                && newMedicalRecords.getLastName().equals(medicalRecords.getLastName()));

        // Added a non-existing medical records.
        if (!firstName_LastNameExists) {
            medicalRecordsList.add(newMedicalRecords);
            logger.info("Medical records added");
        } else {
            logger.error("Medical records already exist.");
            throw new IllegalArgumentException("Medical records already exist.");
        }
        return newMedicalRecords;
    }
    /**
     * Update a medical records of the list.
     *
     */
    public MedicalRecords updateMedicalRecords(MedicalRecords newMedicalRecords, String firstName, String lastName) {
        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();

        // Checks that firstName and lastName of the medical records is in the list.
        boolean firstName_LastNameExists = medicalRecordsList.stream().anyMatch(medicalRecords
                -> medicalRecords.getFirstName().equals(firstName)
                && medicalRecords.getLastName().equals(lastName));

        // Update the medical records present in the list.
        if (firstName_LastNameExists && (newMedicalRecords.getFirstName().equals(firstName)
        && newMedicalRecords.getLastName().equals(lastName))) {
            // Run through the medical records list to modify an existing person
            medicalRecordsList.forEach(medicalRecords -> {
                medicalRecords.setBirthdate(newMedicalRecords.getBirthdate());
                medicalRecords.setMedication(newMedicalRecords.getMedication());
                medicalRecords.setAllergies(newMedicalRecords.getAllergies());
                });
            logger.info("Medical records updated");
        } else{
            logger.error("Medical records "  + newMedicalRecords.getFirstName() + " " + newMedicalRecords.getLastName()
                    + " don't exist.");
            throw new NullPointerException("Medical records "  + newMedicalRecords.getFirstName() + " "
                    + newMedicalRecords.getLastName() + " don't exist.");
        }
        return newMedicalRecords;
    }
    /**
     * Delete a medical records of the list.
     *
     */
    public String deleteMedicalRecords(MedicalRecords removeMedicalRecords, String firstName, String lastName) {
        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();
        int index = medicalRecordsList.indexOf(removeMedicalRecords);
        // Checks that firstName and lastName of the medical records is in the list.
        boolean firstName_LastNameExists = medicalRecordsList.stream().anyMatch(
                medicalRecords -> (medicalRecords.getFirstName().equals(firstName))
                && (medicalRecords.getLastName().equals(lastName)));

        // Remove the medical records present in the list.
        if (firstName_LastNameExists && (index > -1)) {
            medicalRecordsList.remove(removeMedicalRecords);
            logger.info("Medical records deleted.");
            return "Medical records deleted.";
        } else {
            logger.error("Medical records not found.");
            return "Medical records not found.";
        }
    }
}

