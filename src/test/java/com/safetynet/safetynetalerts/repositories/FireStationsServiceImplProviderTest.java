package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.FireStations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FireStationsServiceImplProviderTest {
    @Autowired
    private FireStationsRepository fireStationsRepository;
    @Autowired
    private FireStationsServiceImplProvider fireStationsServiceImplProvider;

    @Test
    void getFireStations() {
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();

        assertNotNull(fireStationsList);
        assertTrue(fireStationsList.size() > 0);
    }

    @Test
    void addFireStations() {
        FireStations newFireStations = new FireStations("15 St James", "5");
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        fireStationsList.add(fireStationsServiceImplProvider.addFireStations(newFireStations));

        assertThat(fireStationsList).contains(newFireStations);
    }

    @Test
    void addFireStations_AlreadyExist() {
        FireStations newFireStations = new FireStations("1509 Culver St", "3");
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();

        assertThrows(IllegalArgumentException.class, () ->
                fireStationsList.add(fireStationsServiceImplProvider.addFireStations(newFireStations)));
    }

    @Test
    void updateFireStations() {
        FireStations newFireStations = new FireStations("15 St James", "5");
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        fireStationsList.add(fireStationsServiceImplProvider
                .updateFireStations(newFireStations, "1509 Culver St"));

        assertThat(fireStationsList).contains(newFireStations);
    }

    @Test
    void updateFireStations_NotExist() {
        FireStations fireStations = new FireStations("15 St James", "5");
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();

        assertThrows(NullPointerException.class, () ->
                fireStationsList.add(fireStationsServiceImplProvider
                        .updateFireStations(fireStations,  "15 St James")));
    }

    @Test
    void deleteFireStations() {
        FireStations removeFireStations = new FireStations("1509 Culver St", "3");

        assertEquals("Fire station deleted", fireStationsServiceImplProvider
                .deleteFireStations(removeFireStations));
    }
}