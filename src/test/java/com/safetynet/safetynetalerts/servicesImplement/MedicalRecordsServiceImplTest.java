package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.repositories.MedicalRecordsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicalRecordsServiceImplTest {
    @Autowired
    protected MedicalRecordsServiceImpl medicalRecordsService;
    @MockBean
    protected MedicalRecordsRepository medicalRecordsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        medicalRecordsService = new MedicalRecordsServiceImpl(medicalRecordsRepository);
    }

    @Test
    void getMedicalRecords() {
        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        medicalRecordsList.add(new MedicalRecords("John", "Boyd", "06/06/1984",
                new ArrayList<>(), new ArrayList<>()));

        when(medicalRecordsRepository.getMedicalRecords()).thenReturn(medicalRecordsList);

        assertEquals(1, medicalRecordsService.getMedicalRecords().size());
    }

    @Test
    void addMedicalRecords() {
        MedicalRecords newMedicalRecords = new MedicalRecords("John", "Boyd", "06/06/1984",
                new ArrayList<>(), new ArrayList<>());

        List<MedicalRecords> medicalRecordsList = medicalRecordsService.addMedicalRecords(newMedicalRecords);
        medicalRecordsList.add(newMedicalRecords);

        when(medicalRecordsRepository.getMedicalRecords()).thenReturn(medicalRecordsList);
        when(medicalRecordsRepository.addMedicalRecords(newMedicalRecords)).thenReturn(newMedicalRecords);

        assertEquals(1, medicalRecordsList.size());
    }

    @Test
    void updateMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords = new MedicalRecords(firstName, lastName, "06/06/1984",
                        new ArrayList<>(), new ArrayList<>());

        List<MedicalRecords> medicalRecordsList = medicalRecordsService
                .updateMedicalRecords(newMedicalRecords, firstName, lastName);
        medicalRecordsList.add(newMedicalRecords);

        when(medicalRecordsRepository.updateMedicalRecords(newMedicalRecords, firstName, lastName))
                .thenReturn(newMedicalRecords);

        assertEquals(1, medicalRecordsList.size());
    }

    @Test
    void deleteMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords removeMedicalRecords = new MedicalRecords(firstName, lastName, "06/06/1984",
                new ArrayList<>(), new ArrayList<>());
        String medicalRecordsString = medicalRecordsService
                .deleteMedicalRecords(removeMedicalRecords, firstName, lastName);

        assertNull(medicalRecordsString);
    }
}