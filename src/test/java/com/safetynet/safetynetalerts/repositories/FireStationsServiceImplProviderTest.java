package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.FireStations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FireStationsServiceImplProviderTest {
    @Autowired
    private FireStationsServiceImplProvider fireStationsProvider;
    @MockBean
    protected FireStationsRepository fireStationsRepository;

    @BeforeEach
    void setUp() {
        fireStationsProvider = new FireStationsServiceImplProvider(fireStationsRepository);
    }

    @Test
    void getFireStations() {
        assertNotNull(fireStationsProvider.getFireStations());
    }

    @Test
    void addFireStations_AlreadyExist() {
        List<FireStations> fireStationsList = new ArrayList<>();
        FireStations newFireStations  = new FireStations("1509 Culver St", "3");

        fireStationsList.add(newFireStations);

        when(fireStationsRepository.getFireStations()).thenReturn(fireStationsList);

        String errorMessage = "Fire station already exist.";
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class,
                () -> fireStationsProvider.addFireStations(newFireStations), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void addFireStations() {
        FireStations newFireStations = new FireStations("15 St James", "5");
        List<FireStations> fireStationsList = fireStationsProvider.getFireStations();

        assertThat(fireStationsList).doesNotContain(fireStationsProvider
                .addFireStations(newFireStations));
    }

    @Test
    void updateFireStations_NotExist() {
        String address = "15 St James";
        FireStations newFireStations = new FireStations(address, "5");
        String errorMessage = "Fire stations address don't exist.";

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> fireStationsProvider.updateFireStations(newFireStations, address), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void updateFireStations() {
        String address = "1509 Culver St";
        List<FireStations> fireStationsList = new ArrayList<>();
        FireStations newFireStations  = new FireStations(address, "3");

        fireStationsList.add(newFireStations);
        fireStationsList.add(new FireStations("15 St James", "5"));

        when(fireStationsRepository.getFireStations()).thenReturn(fireStationsList);

        assertEquals(newFireStations, fireStationsProvider
                .updateFireStations(newFireStations, address));
    }

    @Test
    void deleteFireStations_NotExist() {
        List<FireStations> fireStationsList = new ArrayList<>();
        FireStations removeFireStations = new FireStations("15 St James", "5");

        fireStationsList.add(null);
        fireStationsList.add(new FireStations("1509 Culver St", "3"));

        when(fireStationsRepository.getFireStations()).thenReturn(fireStationsList);

        String fireStationString = fireStationsProvider.deleteFireStations(removeFireStations);
        assertEquals("Fire station not found.", fireStationString);
    }

    @Test
    void deleteFireStations() {
        List<FireStations> fireStationsList = new ArrayList<>();
        FireStations removeFireStations = new FireStations("1509 Culver St", "3");

        fireStationsList.add(removeFireStations);
        fireStationsList.add(new FireStations("15 St James", "5"));

        when(fireStationsRepository.getFireStations()).thenReturn(fireStationsList);

        String fireStationString = fireStationsProvider.deleteFireStations(removeFireStations);
        assertEquals("Fire station deleted.", fireStationString);
    }
}