package com.safetynet.safetynetalerts.utils;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.models.FireStations;
import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.models.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReadData {
    private static final Logger logger = LogManager.getLogger("ReadData");

    /**
     * Method to get the fire stations info's from JSON file.
     *
     */
    public List<FireStations> loadFireStations() {
        List<FireStations> fireStationsList = new ArrayList<>();
        String path = "src/main/resources/data.json";

        try {
            // Write into binary format.
            byte[] bytesFile = Files.readAllBytes(new File(path).toPath());
            // Convert and read the binary format.
            JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
            Any any = jsonIterator.readAny();
            Any fireStationsAny = any.get("firestations");

            // Add json string to the list.
            fireStationsAny.forEach(item -> fireStationsList.add(
                    new FireStations(
                            item.get("address").toString(),
                            item.get("station").toString()
                    )));
            return fireStationsList;

        } catch (IOException e) {
            logger.error("Failed to convert JSON file for FireStationsRepository", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * Method to get the medical records info's from JSON file.
     *
     */
    public List<MedicalRecords> loadMedicalRecords() {
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
     * Method to get the persons info's from JSON file.
     *
     */
    public List<Persons> loadPersons() {
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
                    getOne(item.get("firstName").toString(),
                            item.get("lastName").toString())
            )));
            return personsList;

        } catch (IOException e) {
            logger.error("Failed to convert JSON file PersonsRepository", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * Method to filter the medical records info's from JSON file.
     * Compares the first and last names of people to add the medical records that correspond to them.
     *
     */
    public MedicalRecords getOne(String firstName, String lastName) {
        Optional<MedicalRecords> medicalRecordsOptional = loadMedicalRecords().stream().filter(
                element -> element.getFirstName().equals(firstName) && element.getLastName().equals(lastName)).findFirst();
        return medicalRecordsOptional.orElseGet(() -> getOne(firstName, lastName));
    }
}
