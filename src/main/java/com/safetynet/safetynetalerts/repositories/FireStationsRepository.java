package com.safetynet.safetynetalerts.repositories;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.models.FireStations;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
/**
 * Contains method to convert String to JSON object for fire stations.
 *
 * @author Terry
 */
@Repository
@AllArgsConstructor
public class FireStationsRepository {
    private static final Logger logger = LogManager.getLogger("FireStationsRepository");
    /**
     * Method to get the fire stations info's from JSON file.
     *
     */
    public List<FireStations> getFireStations() {
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
}