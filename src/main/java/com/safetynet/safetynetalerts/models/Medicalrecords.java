package com.safetynet.safetynetalerts.models;

import com.jsoniter.any.Any;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Data

public class Medicalrecords {
    private Persons person;
    private Date birthdate;
    private List<Any> medication;
    private List<Any> allergies;

    public Medicalrecords(Persons person, String birthdate, List<Any> medication, List<Any> allergies) {
        this.person = person;
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
                "firstname='" + person.getFirstname() + '\'' +
                ", lastname='" + person.getLastname() + '\'' +
                ", birthdate=" + birthdate +
                ", medication=" + medication +
                ", allergies=" + allergies +
                '}';
    }

    public Long getAge() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(this.getBirthdate());

        LocalDate start = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        LocalDate stop = LocalDate.now(ZoneId.of("Europe/Paris"));

        return java.time.temporal.ChronoUnit.YEARS.between(start, stop);
    }
}
