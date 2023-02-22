package com.safetynet.safetynetalerts.repositories;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.models.MedicalRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Method to get the medical records info's from JSON file.
 *
 */
@Repository
public class MedicalRecordsRepository {
    private static final Logger logger = LogManager.getLogger("MedicalRecordsRepository");
    /**
     * Method to get the medical records info's from JSON file.
     *
     */
    public List<MedicalRecords> getMedicalRecords() {
        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        String path = "src/main/resources/data.json";

        try {
            // Write into binary format.
            byte[] bytesFile = Files.readAllBytes(new File(path).toPath());
            // Convert and read the binary format.
            JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
            Any any = jsonIterator.readAny();
            Any medicalRecordsAny = any.get("medicalrecords");

            // Add json string to the list.
            medicalRecordsAny.forEach(item -> medicalRecordsList.add(
                    new MedicalRecords(
                            item.get("firstName").toString(),
                            item.get("lastName").toString(),
                            item.get("birthdate").toString(),
                            item.get("medications").asList(),
                            item.get("allergies").asList()
            )));
            return medicalRecordsList;

        } catch (IOException e) {
            logger.error("Failed to convert JSON file MedicalRecordsRepository", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * Method to filter the medical records info's from JSON file.
     *
     */
    public MedicalRecords getOne(String firstName, String lastName) {
        Optional<MedicalRecords> medicalRecordsOptional = getMedicalRecords().stream().filter(
                element -> element.getFirstName().equals(firstName) && element.getLastName().equals(lastName)).findFirst();
        return medicalRecordsOptional.orElseGet(() -> getOne(firstName, lastName));
    }
}