package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.MedicalRecords;
import com.safetynet.safetynetalerts.repositories.MedicalRecordsRepository;
import com.safetynet.safetynetalerts.repositories.MedicalRecordsServiceImplProvider;
import javafx.beans.binding.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MedicalRecordsServiceImplTest {
    @MockBean
    protected MedicalRecordsServiceImpl medicalRecordsServiceImpl;
    @MockBean
    protected MedicalRecordsServiceImplProvider medicalRecordsServiceImplProvider;

    @BeforeEach
    void setUp() {
        medicalRecordsServiceImplProvider = mock(MedicalRecordsServiceImplProvider.class);
        medicalRecordsServiceImpl = new MedicalRecordsServiceImpl(medicalRecordsServiceImplProvider);
    }

    @Test
    void getMedicalRecords() {
        when(medicalRecordsServiceImpl.getMedicalRecords()).thenReturn(medicalRecordsServiceImplProvider.getMedicalRecords());

        assertNotEquals(medicalRecordsServiceImplProvider.getMedicalRecords(), new ArrayList<>());
    }

    @Test
    void addMedicalRecords() {

    }

    @Test
    void updateMedicalRecords() {
    }

    @Test
    void deleteMedicalRecords() {
    }
}