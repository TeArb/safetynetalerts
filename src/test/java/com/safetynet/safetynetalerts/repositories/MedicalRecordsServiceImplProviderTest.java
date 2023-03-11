package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicalRecordsServiceImplProviderTest {
    @Autowired
    private MedicalRecordsServiceImplProvider medicalRecordsProvider;
    @MockBean
    protected MedicalRecordsRepository medicalRecordsRepository;

    @BeforeEach
    void setUp() {
        medicalRecordsProvider = new MedicalRecordsServiceImplProvider(medicalRecordsRepository);
    }

    @Test
    void getMedicalRecords() {
        assertNotNull(medicalRecordsProvider.getMedicalRecords());
    }

    @Test
    void addMedicalRecords_AlreadyExist() {
        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        MedicalRecords newMedicalRecords  = new MedicalRecords("John", "Boyd",
                new Date(), new ArrayList<>(), new ArrayList<>());

        medicalRecordsList.add(newMedicalRecords);

        when(medicalRecordsRepository.getMedicalRecords()).thenReturn(medicalRecordsList);

        String errorMessage = "Medical records already exist.";
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class,
                () -> medicalRecordsProvider.addMedicalRecords(newMedicalRecords), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void addMedicalRecords() {
        MedicalRecords newMedicalRecords = new MedicalRecords();
        List<MedicalRecords> medicalRecordsList = medicalRecordsProvider.getMedicalRecords();

        newMedicalRecords.setFirstName("Jake");
        newMedicalRecords.setLastName("Doe");
        newMedicalRecords.setBirthdate(new Date("03/06/1984"));
        newMedicalRecords.setMedication(List.of("aznol:350mg", "hydrapermazol:100mg]"));
        newMedicalRecords.setAllergies(List.of("peanuts"));

        assertThat(medicalRecordsList).doesNotContain(medicalRecordsProvider.addMedicalRecords(newMedicalRecords));
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
                () -> medicalRecordsProvider
                        .updateMedicalRecords(newMedicalRecords, "John", "Boyd"), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void updateMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        MedicalRecords newMedicalRecords  = new MedicalRecords(firstName, lastName,
                new Date(), new ArrayList<>(), new ArrayList<>());

        medicalRecordsList.add(newMedicalRecords);
        medicalRecordsList.add(new MedicalRecords("John", "Doe",
                new Date(), new ArrayList<>(), new ArrayList<>()));

        when(medicalRecordsRepository.getMedicalRecords()).thenReturn(medicalRecordsList);

        assertEquals(newMedicalRecords, medicalRecordsProvider.
                updateMedicalRecords(newMedicalRecords, firstName, lastName));
    }

    @Test
    void deleteMedicalRecords_NotExist() {
        String firstName = "Jake";
        String lastName = "Doe";
        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        MedicalRecords removeMedicalRecords  = new MedicalRecords(firstName, lastName, new Date(),
                new ArrayList<>(), new ArrayList<>());

        medicalRecordsList.add(removeMedicalRecords);
        medicalRecordsList.add(new MedicalRecords("John", "Doe", new Date(),
                new ArrayList<>(), new ArrayList<>()));

        when(medicalRecordsRepository.getMedicalRecords()).thenReturn(medicalRecordsList);

        String  medicalRecordString = medicalRecordsProvider.deleteMedicalRecords(removeMedicalRecords, null, null);
        assertEquals("Medical records not found.", medicalRecordString);
    }

    @Test
    void deleteMedicalRecords() {
        String firstName = "Jake";
        String lastName = "Doe";
        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        MedicalRecords removeMedicalRecords  = new MedicalRecords("Jake", "Doe", new Date(),
                new ArrayList<>(), new ArrayList<>());

        medicalRecordsList.add(removeMedicalRecords);
        medicalRecordsList.add(new MedicalRecords("John", "Doe", new Date(),
                new ArrayList<>(), new ArrayList<>()));

        when(medicalRecordsRepository.getMedicalRecords()).thenReturn(medicalRecordsList);

        String result = medicalRecordsProvider.deleteMedicalRecords(removeMedicalRecords, firstName, lastName);
        assertEquals("Medical records deleted.", result);
    }
}