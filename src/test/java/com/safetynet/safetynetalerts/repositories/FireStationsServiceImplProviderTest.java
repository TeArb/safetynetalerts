package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.FireStations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class FireStationsServiceImplProviderTest {
    @Autowired
    private FireStationsServiceImplProvider fireStationsServiceImplProvider;

    @Test
    void getFireStations() {
        assertNotNull(fireStationsServiceImplProvider.getFireStations());
    }

    @Test
    void addFireStations_AlreadyExist() {
        FireStations newFireStations = new FireStations("1509 Culver St", "3");
        String errorMessage = "Fire station already exist.";

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> fireStationsServiceImplProvider.addFireStations(newFireStations), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void addFireStations() {
        FireStations newFireStations = new FireStations("15 St James", "5");
        List<FireStations> fireStationsList = fireStationsServiceImplProvider.getFireStations();

        assertThat(fireStationsList).doesNotContain(fireStationsServiceImplProvider
                .addFireStations(newFireStations));
    }

    @Test
    void updateFireStations_NotExist() {
        String address = "15 St James";
        FireStations newFireStations = new FireStations(address, "5");
        String errorMessage = "Fire stations address don't exist.";

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> fireStationsServiceImplProvider
                        .updateFireStations(newFireStations, address), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void updateFireStations() {
        String address = "1509 Culver St";
        FireStations newFireStations = new FireStations(address, "3");
        List<FireStations> fireStationsList = fireStationsServiceImplProvider.getFireStations();

        assertTrue(fireStationsList.add(fireStationsServiceImplProvider
                .updateFireStations(newFireStations, address)));
    }

    @Test
    void deleteFireStations_NotExist() {
        FireStations removeFireStations = new FireStations("15 St James", "5");
        String returnMessage = "Fire station not found.";

        FireStationsServiceImplProvider mockFireStationsService = mock(FireStationsServiceImplProvider.class);
        when(mockFireStationsService.deleteFireStations(removeFireStations)).thenReturn(returnMessage);

        assertEquals(returnMessage, mockFireStationsService.deleteFireStations(removeFireStations));
    }

    @Test
    void deleteFireStations() {
        FireStations removeFireStations = new FireStations("1509 Culver St", "3");
        String returnMessage = "Fire station deleted.";

        FireStationsServiceImplProvider mockFireStationsService = mock(FireStationsServiceImplProvider.class);
        when(mockFireStationsService.deleteFireStations(removeFireStations)).thenReturn(returnMessage);

        assertEquals(returnMessage, mockFireStationsService.deleteFireStations(removeFireStations));
    }
}