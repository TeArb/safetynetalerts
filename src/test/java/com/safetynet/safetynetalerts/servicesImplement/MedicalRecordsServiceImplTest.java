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
        medicalRecordsList.add(
                new MedicalRecords("John", "Boyd", "06/06/1984", new ArrayList<>(), new ArrayList<>()));

        when(medicalRecordsServiceImplProvider.getMedicalRecords()).thenReturn(medicalRecordsList);

        assertEquals(1, medicalRecordsServiceImpl.getMedicalRecords().size());
    }

    @Test
    void addMedicalRecords() {
        MedicalRecords medicalRecords =
                new MedicalRecords("John", "Boyd", "06/06/1984", new ArrayList<>(), new ArrayList<>());
        List<MedicalRecords> medicalRecordsList = medicalRecordsServiceImpl.addMedicalRecords(medicalRecords);
        assertEquals(1, medicalRecordsList.size());
    }
/*
    @Test
    void updateMedicalRecords() {
        string firstName = "John";
        String lastName = "Boyd";
        MedicalRecords newMedicalRecords = new MedicalRecords();
        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.getMedicalRecords();

        newMedicalRecords.setBirthdate(new Date("03/06/1984"));
        newMedicalRecords.setMedication(List.of("aznol:350mg", "hydrapermazol:100mg]"));
        newMedicalRecords.setAllergies(List.of("nillacilan"));
        assertThat();*/


    @Test
    void deleteMedicalRecords() {
    }
}