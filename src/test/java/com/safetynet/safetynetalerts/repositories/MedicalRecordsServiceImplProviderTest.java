package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicalRecordsServiceImplProviderTest {
    @MockBean
    protected MedicalRecordsServiceImplProvider medicalRecordsServiceImplProvider;

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Test
    void getMedicalRecords() {
        assertNotNull(medicalRecordsServiceImplProvider.getMedicalRecords());
    }

    @Test
    void addMedicalRecords_AlreadyExist() {/*
        MedicalRecords newMedicalRecords = new MedicalRecords();
        List<MedicalRecords> medicalRecordsList = medicalRecordsServiceImplProvider.getMedicalRecords();

        newMedicalRecords.setFirstName("John");
        newMedicalRecords.setFirstName("Boyd");
        newMedicalRecords.setBirthdate(new Date("03/06/1984"));
        newMedicalRecords.setMedication(List.of("aznol:350mg", "hydrapermazol:100mg]"));
        newMedicalRecords.setAllergies(List.of("peanuts"));

        when(medicalRecordsServiceImplProvider.addMedicalRecords(newMedicalRecords))
                .thenReturn(newMedicalRecords);

        assertEquals(medicalRecordsList.add(newMedicalRecords), medicalRecordsServiceImplProvider
                .addMedicalRecords(newMedicalRecords));*/
    }

    @Test
    void addMedicalRecords() {
        MedicalRecords newMedicalRecords = new MedicalRecords();
        List<MedicalRecords> medicalRecordsList = medicalRecordsServiceImplProvider.getMedicalRecords();

        newMedicalRecords.setFirstName("Jake");
        newMedicalRecords.setFirstName("Doe");
        newMedicalRecords.setBirthdate(new Date("03/06/1984"));
        newMedicalRecords.setMedication(List.of("aznol:350mg", "hydrapermazol:100mg]"));
        newMedicalRecords.setAllergies(List.of("peanuts"));

        when(medicalRecordsServiceImplProvider.addMedicalRecords(newMedicalRecords))
                .thenReturn(newMedicalRecords);

        assertThat(medicalRecordsList).doesNotContain(medicalRecordsServiceImplProvider
                .addMedicalRecords(newMedicalRecords));
    }

    @Test
    void updateMedicalRecords_NotExist() {
     /*   String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords = new MedicalRecords();
        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();

        newMedicalRecords.setBirthdate(new Date("03/06/1984"));
        newMedicalRecords.setMedication(List.of("aznol:350mg", "hydrapermazol:100mg]"));
        newMedicalRecords.setAllergies(List.of("peanuts"));

        when(medicalRecordsServiceImplProvider.updateMedicalRecords(newMedicalRecords, firstName, lastName))
                .thenReturn(newMedicalRecords);

        NullPointerException exception = assertThrows(
                NullPointerException.class, () -> {
                    medicalRecordsServiceImplProvider.updateMedicalRecords(newMedicalRecords, firstName, lastName);
                }
        );

        assertEquals("Medical records firstName and lastName don't exist", exception.getMessage());

        NullPointerException exception;

        exception = assertThrows(NullPointerException.class, newMedicalRecords::notify);
        assertNotNull(exception);

        // Note that Object.wait(...) is an overloaded method with a void return type
        exception = assertThrows(NullPointerException.class, newMedicalRecords::wait);
        assertNotNull(exception);*/
    }

    @Test
    void updateMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords = new MedicalRecords();
        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();

        newMedicalRecords.setBirthdate(new Date("03/06/1984"));
        newMedicalRecords.setMedication(List.of("aznol:350mg", "hydrapermazol:100mg]"));
        newMedicalRecords.setAllergies(List.of("nillacilan"));

        when(medicalRecordsServiceImplProvider.updateMedicalRecords(newMedicalRecords, firstName, lastName))
                .thenReturn(newMedicalRecords);

        assertTrue(medicalRecordsList.add(medicalRecordsServiceImplProvider
                .updateMedicalRecords(newMedicalRecords, firstName, lastName)));
    }

    @Test
    void deleteMedicalRecords_NotExist() {
        String firstName = "Jake";
        String lastName = "Doe";
        MedicalRecords removeMedicalRecords = new MedicalRecords();

        removeMedicalRecords.setFirstName(firstName);
        removeMedicalRecords.setFirstName(lastName);

        when(medicalRecordsServiceImplProvider.deleteMedicalRecords(removeMedicalRecords, firstName, lastName))
                .thenReturn("Medical records not found");

        assertEquals("Medical records not found",
                medicalRecordsServiceImplProvider.deleteMedicalRecords(removeMedicalRecords, firstName, lastName));
    }

    @Test
    void deleteMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords removeMedicalRecords = new MedicalRecords();

        removeMedicalRecords.setFirstName(firstName);
        removeMedicalRecords.setFirstName(lastName);

        when(medicalRecordsServiceImplProvider.deleteMedicalRecords(removeMedicalRecords, firstName, lastName))
                .thenReturn("Medical records deleted");

        assertEquals("Medical records deleted",
                medicalRecordsServiceImplProvider.deleteMedicalRecords(removeMedicalRecords, firstName, lastName));
    }
}