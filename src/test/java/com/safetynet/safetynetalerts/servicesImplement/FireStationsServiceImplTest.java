package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.FireStations;
import com.safetynet.safetynetalerts.repositories.FireStationsRepository;
import com.safetynet.safetynetalerts.repositories.FireStationsServiceImplProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@SpringBootTest
class FireStationsServiceImplTest {
    @Mock
    private FireStationsRepository fireStationsRepository;
    @Mock
    private FireStationsServiceImplProvider fireStationsServiceImplProvider;

    @Test
    void getFireStations() {
        fireStationsRepository.getFireStations();

        verify(fireStationsRepository, Mockito.times(1)).getFireStations();
    }

    @Test
    void addFireStations() {
        FireStations newFireStations = new FireStations("15 St James", "5");
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        fireStationsList.add(fireStationsServiceImplProvider.addFireStations(newFireStations));

        verify(fireStationsServiceImplProvider, Mockito.times(1))
                .addFireStations(any(FireStations.class));
    }

    @Test
    void updateFireStations() {
        FireStations newFireStations = new FireStations("1509 Culver St", "3");
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        fireStationsList.add(fireStationsServiceImplProvider
                .updateFireStations(newFireStations, "1509 Culver St"));

        verify(fireStationsServiceImplProvider, Mockito.times(1))
                .updateFireStations(any(FireStations.class), anyString());
    }

    @Test
    void deleteFireStations() {
        FireStations removeFireStations = new FireStations("1509 Culver St", "3");
        fireStationsServiceImplProvider.deleteFireStations(removeFireStations);

        verify(fireStationsServiceImplProvider, Mockito.times(1))
                .deleteFireStations(any(FireStations.class));
    }
}