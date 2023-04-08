package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalRecordsRepositoryTest {
    @Autowired
    protected MedicalRecordsRepository medicalRecordsRepository;

    @Test
    void getMedicalRecords() {
        assertNotNull(medicalRecordsRepository.getMedicalRecords());
    }

    @Test
    void addMedicalRecords_AlreadyExist() {
        MedicalRecords newMedicalRecords  = new MedicalRecords("John", "Boyd",
                new Date(), new ArrayList<>(), new ArrayList<>());

        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();
        medicalRecordsList.add(newMedicalRecords);

        String errorMessage = "Medical records already exist.";
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class,
                () -> medicalRecordsRepository.addMedicalRecords(newMedicalRecords), errorMessage);

        medicalRecordsList.add(newMedicalRecords);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void addMedicalRecords() {
        MedicalRecords newMedicalRecords  = new MedicalRecords("Jill", "Black",
                new Date(), new ArrayList<>(), new ArrayList<>());

        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();

        assertThat(medicalRecordsList).contains(medicalRecordsRepository
                .addMedicalRecords(newMedicalRecords));
    }

    @Test
    void updateMedicalRecords_NotExist() {
        String firstName = "Jake";
        String lastName = "Doe";
        String errorMessage = "Medical records "  + firstName + " " + lastName + " don't exist.";
        MedicalRecords newMedicalRecords  = new MedicalRecords("Dane", "Fall",
                new Date(), new ArrayList<>(), new ArrayList<>());

        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();
        medicalRecordsList.add(newMedicalRecords);

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> medicalRecordsRepository.updateMedicalRecords(newMedicalRecords, firstName, lastName), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void updateMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords  = new MedicalRecords(firstName, lastName,
                new Date(), new ArrayList<>(), new ArrayList<>());

        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();
        medicalRecordsList.add(newMedicalRecords);

        assertThat(medicalRecordsList).contains(medicalRecordsRepository
                .updateMedicalRecords(newMedicalRecords, firstName, lastName));
    }

    @Test
    void deleteMedicalRecords_NotExist() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords removeMedicalRecords  = new MedicalRecords("Jake", "Doe", new Date(),
                new ArrayList<>(), new ArrayList<>());

        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();
        medicalRecordsList.add(removeMedicalRecords);

        String medicalRecordsString = medicalRecordsRepository.deleteMedicalRecords(removeMedicalRecords, firstName, lastName);
        assertEquals("Medical records not found.", medicalRecordsString);
    }

    @Test
    void deleteMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords removeMedicalRecords  = new MedicalRecords(firstName, lastName, new Date(),
                new ArrayList<>(), new ArrayList<>());

        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();
        medicalRecordsList.add(removeMedicalRecords);

        String medicalRecordsString = medicalRecordsRepository.deleteMedicalRecords(removeMedicalRecords, firstName, lastName);
        assertEquals("Medical records deleted.", medicalRecordsString);
    }
}