package com.safetynet.safetynetalerts.repositories;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.models.Medicalrecords;
import com.safetynet.safetynetalerts.models.Persons;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MedicalrecordsRepository {
    public List<Medicalrecords> getMedicalrecords() {
        List<Medicalrecords> medicalrecordsList = new ArrayList<>();
        String path = "data.json";

        try {
            byte[] bytesFile = Files.readAllBytes(new File(path).toPath());
            JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
            Any any = jsonIterator.readAny();
            Any medicalrecordsAny = any.get("medicalrecords");

            medicalrecordsAny.forEach(item -> medicalrecordsList.add(new Medicalrecords(
                    new Persons(item.get("firstName").toString(),
                        item.get("lastName").toString()),
                    new Date(item.get("birthdate").toString()),
                    item.get("medications").asList(),
                    item.get("allergies").asList()
            )));
            return medicalrecordsList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
