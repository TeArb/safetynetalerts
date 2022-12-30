package com.safetynet.safetynetalerts.models;

import com.jsoniter.any.Any;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Data

public class Medicalrecords {
    private String firstName;
    private String lastName;
    private Date birthdate;
    private List<String> medication = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();

    public Medicalrecords(String firstName, String lastName, String birthdate, List<Any> medication, List<Any> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        try {
            this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        medication.forEach(medicationList -> this.medication.add(medicationList.toString()));
        allergies.forEach(allergiesList -> this.allergies.add(allergiesList.toString()));
    }

    @Override
    public String toString() {
        return "Medicalrecords{" +
                "firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", medication=" + medication +
                ", allergies=" + allergies +
                '}';
    }
}