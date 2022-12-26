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
    private List<Any> medication;
    private List<Any> allergies;

    public Medicalrecords(String firstName, String lastName, String birthdate, List<Any> medication, List<Any> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        try {
            this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.medication = medication;
        this.allergies = allergies;
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