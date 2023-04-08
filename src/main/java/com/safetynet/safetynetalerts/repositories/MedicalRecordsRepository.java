package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.constants.DataLoader;
import com.safetynet.safetynetalerts.models.MedicalRecords;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Method to get the medical records info's from JSON file.
 *
 */
@Repository
@AllArgsConstructor
public class MedicalRecordsRepository {
    private static final Logger logger = LogManager.getLogger("MedicalRecordsRepository");

    /**
     * Get a medical records list.
     *
     */
    public List<MedicalRecords> getMedicalRecords() {
        logger.info("Medical records got");
        return DataLoader.MEDICAL_RECORDS_LIST;
    }
    /**
     * Add a medical records.
     *
     */
    public MedicalRecords addMedicalRecords(@NotNull MedicalRecords newMedicalRecords) {
        // Checks that firstName and lastName of the medical records is in the list.
        boolean firstName_LastNameExists = DataLoader.MEDICAL_RECORDS_LIST.stream().anyMatch(
                medicalRecords -> newMedicalRecords.getFirstName().equals(medicalRecords.getFirstName())
                && newMedicalRecords.getLastName().equals(medicalRecords.getLastName()));

        // Added a non-existing medical records.
        if (!firstName_LastNameExists) {
            DataLoader.MEDICAL_RECORDS_LIST.add(newMedicalRecords);
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
        // Checks that firstName and lastName of the medical records is in the list.
        boolean firstName_LastNameExists = DataLoader.MEDICAL_RECORDS_LIST.stream().anyMatch(
                medicalRecords -> firstName.equals(medicalRecords.getFirstName())
                && lastName.equals(medicalRecords.getLastName()));

        // Update the medical records present in the list.
        if (firstName_LastNameExists && (firstName.equals(newMedicalRecords.getFirstName())
                && lastName.equals(newMedicalRecords.getLastName()))) {
            // Run through the medical records list to modify an existing person
            DataLoader.MEDICAL_RECORDS_LIST.forEach(medicalRecord -> {
                if (medicalRecord.getFirstName().equals(firstName)
                        && medicalRecord.getLastName().equals(lastName)) {
                    medicalRecord.setBirthdate(newMedicalRecords.getBirthdate());
                    medicalRecord.setMedication(newMedicalRecords.getMedication());
                    medicalRecord.setAllergies(newMedicalRecords.getAllergies());
                }
            });
            logger.info("Medical records updated");
        } else{
            logger.error("Medical records "  + firstName + " " + lastName + " don't exist.");
            throw new NullPointerException("Medical records "  + firstName + " " + lastName + " don't exist.");
        }
        return newMedicalRecords;
    }
    /**
     * Delete a medical records of the list.
     *
     */
    public String deleteMedicalRecords(MedicalRecords removeMedicalRecords, String firstName, String lastName) {
        int index = DataLoader.MEDICAL_RECORDS_LIST.indexOf(removeMedicalRecords);
        // Checks that firstName and lastName of the medical records is in the list.
        boolean firstName_LastNameExists = DataLoader.MEDICAL_RECORDS_LIST.stream().anyMatch(
                medicalRecord -> firstName.equals(medicalRecord.getFirstName())
                        && lastName.equals(medicalRecord.getLastName()));

        // Remove the medical records present in the list.
        if (firstName_LastNameExists && (index > -1) && (firstName.equals(removeMedicalRecords.getFirstName())
                && lastName.equals(removeMedicalRecords.getLastName()))) {
            DataLoader.MEDICAL_RECORDS_LIST.remove(removeMedicalRecords);
            logger.info("Medical records deleted.");
            return "Medical records deleted.";
        } else {
            logger.error("Medical records not found.");
            return "Medical records not found.";
        }
    }
}