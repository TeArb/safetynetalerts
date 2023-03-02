package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.FireStations;
import com.safetynet.safetynetalerts.repositories.FireStationsServiceImplProvider;
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
class FireStationsServiceImplTest {
    @Autowired
    protected FireStationsServiceImpl fireStationsServiceImpl;
    @MockBean
    protected FireStationsServiceImplProvider fireStationsServiceImplProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fireStationsServiceImpl = new FireStationsServiceImpl(fireStationsServiceImplProvider);
    }

    @Test
    void getFireStations() {
        List<FireStations> fireStationsList = new ArrayList<>();
        fireStationsList.add(new FireStations("1509 Culver St", "3"));

        when(fireStationsServiceImplProvider.getFireStations()).thenReturn(fireStationsList);

        assertEquals(1, fireStationsServiceImpl.getFireStations().size());
    }

    @Test
    void addFireStations() {
        FireStations newFireStations = new FireStations("1509 Culver St", "3");
        List<FireStations> fireStationsList = fireStationsServiceImpl.addFireStations(newFireStations);
        assertEquals(1, fireStationsList.size());
    }

    @Test
    void updateFireStations() {
        String address = "1509 Culver St";
        FireStations newFireStations = new FireStations(address, "3");
        List<FireStations> fireStationsList = fireStationsServiceImpl.updateFireStations(newFireStations, address);

        assertEquals(1, fireStationsList.size());
    }

    @Test
    void deleteFireStations() {
        String address = "1509 Culver St";
        FireStations removeFireStations = new FireStations(address, "3");
        String medicalRecordsString = fireStationsServiceImpl
                .deleteFireStations(removeFireStations);

        assertNull(medicalRecordsString);
    }
}