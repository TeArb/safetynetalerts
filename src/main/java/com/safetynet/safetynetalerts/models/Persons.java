package com.safetynet.safetynetalerts.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

@Data
@AllArgsConstructor
public class Persons {
    private String firstname ;
    private String lastname;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    private Medicalrecords medicalrecords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persons persons = (Persons) o;
        return Objects.equals(firstname, persons.firstname) && Objects.equals(lastname, persons.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

    @Override
    public String toString() {
        return "Persons{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getAge() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(this.medicalrecords.getBirthdate());

        LocalDate start = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        LocalDate stop = LocalDate.now(ZoneId.of("Europe/Paris"));

        int test = (int) ChronoUnit.YEARS.between(start, stop);
        System.out.println("" + test);

        return (int) ChronoUnit.YEARS.between(start, stop);
    }
}