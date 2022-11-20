package com.safetynet.safetynetalerts.repositories;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.models.Persons;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonsRepository {
    public List<Persons> getPersons() {
        List<Persons> personsList = new ArrayList<>();
        String path = "data.json";

        try {
            byte[] bytesFile = Files.readAllBytes(new File(path).toPath());
            JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
            Any any = jsonIterator.readAny();
            Any personsAny = any.get("persons");

            personsAny.forEach(item -> personsList.add(new Persons(
                    item.get("firstName").toString(),
                    item.get("lastName").toString(),
                    item.get("address").toString(),
                    item.get("city").toString(),
                    item.get("zip").toString(),
                    item.get("phone").toString(),
                    item.get("email").toString()
            )));
            return personsList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
