package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.FireStations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FireStationsRepositoryTest {
    @Autowired
    private FireStationsRepository fireStationsRepository;

    @Test
    void getFireStations() {
        assertNotNull(fireStationsRepository.getFireStations());
    }

    @Test
    void addFireStations_AlreadyExist()  {
        FireStations newFireStation  = new FireStations("1505 Rockwell", "1");

        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        fireStationsList.add(newFireStation);

        String errorMessage = "Fire station already exist.";
        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class,
                () -> fireStationsRepository.addFireStations(newFireStation), errorMessage);

        fireStationsList.add(newFireStation);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void addFireStations() {
        FireStations newFireStation = new FireStations("1505 Rockwell", "1");

        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();

        assertThat(fireStationsList).contains(fireStationsRepository
                .addFireStations(newFireStation));
    }

    @Test
    void updateFireStations_NotExist() {
        FireStations fireStation = new FireStations("1303 Malt St", "2");
        String errorMessage = "Fire stations address don't exist.";

        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        fireStationsList.add(fireStation);

        NullPointerException thrownException = assertThrows(NullPointerException.class,
                () -> fireStationsRepository.updateFireStations(fireStation, "1030 Pinkhead"), errorMessage);

        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void updateFireStations() {
        String address = "1505 Rockwell";
        FireStations oldFireStation = new FireStations(address, "1");
        FireStations newFireStation = new FireStations("15 St James", "5");

        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        fireStationsList.add(oldFireStation);

        assertThat(fireStationsList).contains(fireStationsRepository
                .updateFireStations(newFireStation, address));
    }

    @Test
    void deleteFireStations_NotExist() {
        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        fireStationsList.add(new FireStations("1303 Malt St", "1"));

        String fireStationString = fireStationsRepository
                .deleteFireStations(new FireStations("1505 Rockwell", "1"));
        assertEquals("Fire station not found.", fireStationString);
    }

    @Test
    void deleteFireStations() {
        FireStations removeFireStation = new FireStations("1505 Rockwell", "1");

        List<FireStations> fireStationsList = fireStationsRepository.getFireStations();
        fireStationsList.add(removeFireStation);

        String fireStationString = fireStationsRepository.deleteFireStations(removeFireStation);
        assertEquals("Fire station deleted.", fireStationString);
    }
}