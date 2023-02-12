package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.Medicalrecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalrecordsServiceImplProvider {
    private static final Logger logger = LogManager.getLogger("MedicalrecordsServiceImplProvider");
    @Autowired
    private MedicalrecordsRepository medicalrecordsRepository;
    /**
     * Get a medical records list.
     *
     */
    public List<Medicalrecords> getMedicalrecords() {
        logger.info("Medical records got");
        return this.medicalrecordsRepository.getMedicalrecords();
    }
    /**
     * Add a medical records.
     *
     */
    public Medicalrecords addMedicalrecords(Medicalrecords newMedicalrecords) {
        List<Medicalrecords> medicalrecordsList = medicalrecordsRepository.getMedicalrecords();
        // Checks that firstName and lastName of the medical records is in the list.
        boolean firstName_LastNameExsits = medicalrecordsList.stream().anyMatch(medicalrecords
                -> newMedicalrecords.getFirstName().equals(medicalrecords.getFirstName())
                && newMedicalrecords.getLastName().equals(medicalrecords.getLastName()));

        // Added a non-existing medical records.
        if (!firstName_LastNameExsits) {
            medicalrecordsList.add(newMedicalrecords);
            logger.info("Medical records added");
        } else {
            logger.error("Medical records already exist");
            throw new IllegalArgumentException("Medical records already exist");
        }
        return newMedicalrecords;
    }
    /**
     * Update a medical records of the list.
     *
     */
    public Medicalrecords updateMedicalrecords(Medicalrecords newMedicalrecords, String firstName, String lastName) {
        List<Medicalrecords> medicalrecordsList = medicalrecordsRepository.getMedicalrecords();

        // Checks that firstName and lastName of the medical records is in the list.
        boolean firstName_LastNameExsits = medicalrecordsList.stream().anyMatch(medicalrecords
                -> firstName.equals(medicalrecords.getFirstName())
                && lastName.equals(medicalrecords.getLastName()));

        // Update the medical records present in the list.
        if (firstName_LastNameExsits) {
            // Run through the medical records list to modify an existing firstName and lastName
            medicalrecordsList.forEach(medicalrecords -> {
                /*if (medicalrecords.getFirstName().equals(firstName_LastNameExsits)
                        && medicalrecords.getLastName().equals(firstName_LastNameExsits)) {*/
                    medicalrecords.setBirthdate(newMedicalrecords.getBirthdate());
                    medicalrecords.setMedication(newMedicalrecords.getMedication());
                    medicalrecords.setAllergies(newMedicalrecords.getAllergies());
                });
            logger.info("Medical records updated");
        } else{
            logger.error("Medical records firstName and lastName don't exist");
            throw new NullPointerException("Medical records firstName and lastName don't exist");
        }
        return newMedicalrecords;
    }
    /**
     * Delete a medical records of the list.
     *
     */
    public String deleteMedicalrecords(Medicalrecords removeMedicalrecords, String firstName, String lastName) {
        List<Medicalrecords> medicalrecordsList = medicalrecordsRepository.getMedicalrecords();
        int index = medicalrecordsList.indexOf(removeMedicalrecords);
        // Checks that firstName and lastName of the medical records is in the list.
        boolean firstName_LastNameExsits = medicalrecordsList.stream().anyMatch(medicalrecords
                -> firstName.equals(medicalrecords.getFirstName())
                && lastName.equals(medicalrecords.getLastName()));

        // Remove the medical records present in the list.
        if (firstName_LastNameExsits && (index > -1)) {
            medicalrecordsList.remove(removeMedicalrecords);
            logger.info("Medical records deleted");
            return "Medical records deleted";
        } else {
            logger.error("Medical records not found");
            return "Medical records not found";
        }
    }
}

