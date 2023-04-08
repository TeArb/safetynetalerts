package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.FireStations;
import com.safetynet.safetynetalerts.repositories.FireStationsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FireStationsServiceImplTest {
    @Autowired
    private FireStationsServiceImpl fireStationsService;
    @MockBean
    protected FireStationsRepository fireStationsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fireStationsService = new FireStationsServiceImpl(fireStationsRepository);
    }

    @Test
    void getFireStations() {
        List<FireStations> fireStationsList = new ArrayList<>();
        fireStationsList.add(new FireStations("1509 Culver St", "3"));

        when(fireStationsRepository.getFireStations()).thenReturn(fireStationsList);

        assertEquals(1, fireStationsService.getFireStations().size());
    }

    @Test
    void addFireStations() {
        FireStations newFireStation = new FireStations("1480 Clever", "1");

        List<FireStations> fireStationsList = fireStationsService.addFireStations(newFireStation);
        fireStationsList.add(newFireStation);

        when(fireStationsRepository.getFireStations()).thenReturn(fireStationsList);
        when(fireStationsRepository.addFireStations(newFireStation)).thenReturn(newFireStation);

        assertEquals(1, fireStationsList.size());
    }

    @Test
    void updateFireStations() {
        String address = "830 Colline Baker";
        FireStations newFireStation = new FireStations("1480 Clever", "1");

        List<FireStations> fireStationsList = fireStationsService.updateFireStations(newFireStation, address);
        fireStationsList.add(newFireStation);

        when(fireStationsRepository.updateFireStations(newFireStation, address))
                .thenReturn(newFireStation);

        assertEquals(1, fireStationsList.size());
    }

    @Test
    void deleteFireStations() {
        FireStations removeFireStations = new FireStations("1509 Culver St", "3");
        String medicalRecordsString = fireStationsService
                .deleteFireStations(removeFireStations);

        assertNull(medicalRecordsString);
    }
}