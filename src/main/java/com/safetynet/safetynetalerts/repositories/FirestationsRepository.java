package com.safetynet.safetynetalerts.repositories;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.models.Firestations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class FirestationsRepository {
    public List<Firestations> getFirestations() {
        List<Firestations> firestationsList = new ArrayList<>();
        String path = "data.json";

        try {
            byte[] bytesFile = Files.readAllBytes(new File(path).toPath());
            JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
            Any any = jsonIterator.readAny();
            Any firestationsAny = any.get("firestations");

            firestationsAny.forEach(item -> firestationsList.add(new Firestations(
                    item.get("address").toString(),
                    item.get("station").toString()
            )));
            return firestationsList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
