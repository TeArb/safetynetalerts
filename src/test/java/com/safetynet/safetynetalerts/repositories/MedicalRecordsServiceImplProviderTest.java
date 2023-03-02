package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicalRecordsServiceImplProviderTest {
    @Autowired
    protected MedicalRecordsServiceImplProvider medicalRecordsServiceImplProvider;

    @Test
    void getMedicalRecords() {
        assertNotNull(medicalRecordsServiceImplProvider.getMedicalRecords());
    }

    @Test
    void addMedicalRecords_AlreadyExist() {
        MedicalRecords newMedicalRecords = new MedicalRecords();
        String errorMessage = "Medical records already exist.";

        newMedicalRecords.setFirstName("John");
        newMedicalRecords.setLastName("Boyd");

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> medicalRecordsServiceImplProvider.addMedicalRecords(newMedicalRecords), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void addMedicalRecords() {
        MedicalRecords newMedicalRecords = new MedicalRecords();
        List<MedicalRecords> medicalRecordsList = medicalRecordsServiceImplProvider.getMedicalRecords();

        newMedicalRecords.setFirstName("Jake");
        newMedicalRecords.setLastName("Doe");
        newMedicalRecords.setBirthdate(new Date("03/06/1984"));
        newMedicalRecords.setMedication(List.of("aznol:350mg", "hydrapermazol:100mg]"));
        newMedicalRecords.setAllergies(List.of("peanuts"));

        assertThat(medicalRecordsList).doesNotContain(medicalRecordsServiceImplProvider
                .addMedicalRecords(newMedicalRecords));
    }

    @Test
    void updateMedicalRecords_NotExist() {
        String firstName = "Jake";
        String lastName = "Doe";
        MedicalRecords newMedicalRecords = new MedicalRecords();
        String errorMessage = "Medical records "  + firstName + " " + lastName + " don't exist.";

        newMedicalRecords.setFirstName(firstName);
        newMedicalRecords.setLastName(lastName);

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> medicalRecordsServiceImplProvider
                        .updateMedicalRecords(newMedicalRecords, "John", "Boyd"), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void updateMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords = new MedicalRecords();
        List<MedicalRecords> medicalRecordsList = medicalRecordsServiceImplProvider.getMedicalRecords();

        newMedicalRecords.setFirstName(firstName);
        newMedicalRecords.setLastName(lastName);

        assertTrue(medicalRecordsList.add(medicalRecordsServiceImplProvider
                .updateMedicalRecords(newMedicalRecords, firstName, lastName)));
    }

    @Test
    void deleteMedicalRecords_NotExist() {
        String firstName = "Jake";
        String lastName = "Doe";
        String returnMessage = "Medical records not found.";
        MedicalRecords removeMedicalRecords = new MedicalRecords();

        MedicalRecordsServiceImplProvider mockMedicalRecordsService = mock(MedicalRecordsServiceImplProvider.class);
        when(mockMedicalRecordsService.deleteMedicalRecords(removeMedicalRecords, firstName, lastName))
                .thenReturn(returnMessage);

        assertEquals(returnMessage, mockMedicalRecordsService.deleteMedicalRecords(
                removeMedicalRecords, firstName, lastName));
    }

    @Test
    void deleteMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        String returnMessage = "Medical records deleted.";
        MedicalRecords removeMedicalRecords = new MedicalRecords();

        MedicalRecordsServiceImplProvider mockMedicalRecordsService = mock(MedicalRecordsServiceImplProvider.class);
        when(mockMedicalRecordsService.deleteMedicalRecords(removeMedicalRecords, firstName, lastName))
                .thenReturn(returnMessage);

        assertEquals(returnMessage, mockMedicalRecordsService.deleteMedicalRecords(
                removeMedicalRecords, firstName, lastName));
    }
}