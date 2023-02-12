package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.Firestations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FirestationsServiceImplProviderFirstNameAndLastName {
    @Autowired
    private FirestationsRepository firestationsRepository;
    @Autowired
    private FirestationsServiceImplProvider firestationsServiceImplProvider;

    @Test
    void getFirestations() {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();

        assertNotNull(firestationsList);
        assertTrue(firestationsList.size() > 0);
    }

    @Test
    void addFirestations() {
        Firestations newFirestations = new Firestations("15 St James", "5");
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        firestationsList.add(firestationsServiceImplProvider.addFirestations(newFirestations));

        assertThat(firestationsList).contains(newFirestations);
    }

    @Test
    void addFirestations_AlreadyExist() {
        Firestations newFirestations = new Firestations("1509 Culver St", "3");
        List<Firestations> firestationsList = firestationsRepository.getFirestations();

        assertThrows(IllegalArgumentException.class, () ->
                firestationsList.add(firestationsServiceImplProvider.addFirestations(newFirestations)));
    }

    @Test
    void updateFirestations() {
        Firestations newFirestations = new Firestations("15 St James", "5");
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        firestationsList.add(firestationsServiceImplProvider
                .updateFirestations(newFirestations, "1509 Culver St"));

        assertThat(firestationsList).contains(newFirestations);
    }

    @Test
    void updateFirestations_NotExist() {
        Firestations firestations = new Firestations("15 St James", "5");
        List<Firestations> firestationsList = firestationsRepository.getFirestations();

        assertThrows(NullPointerException.class, () ->
                firestationsList.add(firestationsServiceImplProvider
                        .updateFirestations(firestations,  "15 St James")));
    }

    @Test
    void deleteFirestations() {
        Firestations removeFirestations = new Firestations("1509 Culver St", "3");

        assertEquals("Fire station deleted", firestationsServiceImplProvider
                .deleteFirestations(removeFirestations));
    }
}