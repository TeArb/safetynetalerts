package com.safetynet.safetynetalerts.repositories;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.models.Medicalrecords;
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
public class MedicalrecordsRepository {
    private static final Logger logger = LogManager.getLogger("MedicalrecordsRepository");
    /**
     * Method to get the medical records info's from JSON file.
     *
     */
    public List<Medicalrecords> getMedicalrecords() {
        List<Medicalrecords> medicalrecordsList = new ArrayList<>();
        String path = "src/main/resources/data.json";

        try {
            // Write into binary format.
            byte[] bytesFile = Files.readAllBytes(new File(path).toPath());
            // Convert and read the binary format.
            JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
            Any any = jsonIterator.readAny();
            Any medicalrecordsAny = any.get("medicalrecords");

            // Add json string to the list.
            medicalrecordsAny.forEach(item -> medicalrecordsList.add(
                    new Medicalrecords(
                            item.get("firstName").toString(),
                            item.get("lastName").toString(),
                            item.get("birthdate").toString(),
                            item.get("medications").asList(),
                            item.get("allergies").asList()
            )));
            return medicalrecordsList;

        } catch (IOException e) {
            logger.error("Failed to convert JSON file MedicalrecordsRepository", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * Method to filter the medical records info's from JSON file.
     *
     */
    public Medicalrecords getOne(String firstname, String lastname) {
        Optional<Medicalrecords> medicalrecordsOptional = getMedicalrecords().stream().filter(
                element -> element.getFirstName().equals(firstname) && element.getLastName().equals(lastname)).findFirst();
        return medicalrecordsOptional.orElseGet(() -> getOne(firstname, lastname));
    }
}