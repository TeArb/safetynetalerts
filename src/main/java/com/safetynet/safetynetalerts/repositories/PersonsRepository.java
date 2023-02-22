package com.safetynet.safetynetalerts.repositories;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.models.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
/**
 * Method to get the persons info's from JSON file.
 *
 */
@Repository
public class PersonsRepository {
    private static final Logger logger = LogManager.getLogger("PersonsRepository");
    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;
    /**
     * Method to get the persons info's from JSON file.
     *
     */
    public List<Persons> getPersons() {
        List<Persons> personsList = new ArrayList<>();
        String path = "src/main/resources/data.json";

        try {
            // Write into binary format.
            byte[] bytesFile = Files.readAllBytes(new File(path).toPath());
            // Convert and read the binary format.
            JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
            Any any = jsonIterator.readAny();
            Any personsAny = any.get("persons");

            // Add json string to the list.
            personsAny.forEach(item -> personsList.add(new Persons(
                    item.get("firstName").toString(),
                    item.get("lastName").toString(),
                    item.get("address").toString(),
                    item.get("city").toString(),
                    item.get("zip").toString(),
                    item.get("phone").toString(),
                    item.get("email").toString(),
                    // To add medical record info's according to people names.
                    medicalRecordsRepository.getOne(item.get("firstName").toString(),
                            item.get("lastName").toString())
            )));
            return personsList;

        } catch (IOException e) {
            logger.error("Failed to convert JSON file PersonsRepository", e);
            throw new RuntimeException(e);
        }
    }
}