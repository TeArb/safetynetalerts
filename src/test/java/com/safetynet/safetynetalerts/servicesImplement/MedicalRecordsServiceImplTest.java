package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.repositories.MedicalRecordsServiceImplProvider;
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
    protected MedicalRecordsServiceImpl medicalRecordsServiceImpl;
    @MockBean
    protected MedicalRecordsServiceImplProvider medicalRecordsServiceImplProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        medicalRecordsServiceImpl = new MedicalRecordsServiceImpl(medicalRecordsServiceImplProvider);
    }

    @Test
    void getMedicalRecords() {
        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        medicalRecordsList.add(new MedicalRecords("John", "Boyd", "06/06/1984",
                new ArrayList<>(), new ArrayList<>()));

        when(medicalRecordsServiceImplProvider.getMedicalRecords()).thenReturn(medicalRecordsList);

        assertEquals(1, medicalRecordsServiceImpl.getMedicalRecords().size());
    }

    @Test
    void addMedicalRecords() {
        MedicalRecords newMedicalRecords = new MedicalRecords("John", "Boyd", "06/06/1984",
                new ArrayList<>(), new ArrayList<>());
        List<MedicalRecords> medicalRecordsList = medicalRecordsServiceImpl.addMedicalRecords(newMedicalRecords);

        assertEquals(1, medicalRecordsList.size());
    }

    @Test
    void updateMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords = new MedicalRecords(firstName, lastName, "06/06/1984",
                        new ArrayList<>(), new ArrayList<>());
        List<MedicalRecords> medicalRecordsList = medicalRecordsServiceImpl
                .updateMedicalRecords(newMedicalRecords, firstName, lastName);

        assertEquals(1, medicalRecordsList.size());
    }


    @Test
    void deleteMedicalRecords() {
        String firstName = "John";
        String lastName = "Boyd";
        MedicalRecords removeMedicalRecords = new MedicalRecords(firstName, lastName, "06/06/1984",
                new ArrayList<>(), new ArrayList<>());
        String medicalRecordsString = medicalRecordsServiceImpl
                .deleteMedicalRecords(removeMedicalRecords, firstName, lastName);

        assertNull(medicalRecordsString);
    }
}