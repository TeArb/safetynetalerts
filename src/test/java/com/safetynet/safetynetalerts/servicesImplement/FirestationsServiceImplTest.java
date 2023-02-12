package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.Firestations;
import com.safetynet.safetynetalerts.repositories.FirestationsRepository;
import com.safetynet.safetynetalerts.repositories.FirestationsServiceImplProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@SpringBootTest
class FirestationsServiceImplTest {
    @Mock
    private FirestationsRepository firestationsRepository;
    @Mock
    private FirestationsServiceImplProvider firestationsServiceImplProvider;

    @Test
    void getFirestations() {
        firestationsRepository.getFirestations();

        verify(firestationsRepository, Mockito.times(1)).getFirestations();
    }

    @Test
    void addFirestations() {
        Firestations newFirestations = new Firestations("15 St James", "5");
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        firestationsList.add(firestationsServiceImplProvider.addFirestations(newFirestations));

        verify(firestationsServiceImplProvider, Mockito.times(1))
                .addFirestations(any(Firestations.class));
    }

    @Test
    void updateFirestations() {
        Firestations newFirestations = new Firestations("1509 Culver St", "3");
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        firestationsList.add(firestationsServiceImplProvider
                .updateFirestations(newFirestations, "1509 Culver St"));

        verify(firestationsServiceImplProvider, Mockito.times(1))
                .updateFirestations(any(Firestations.class), anyString());
    }

    @Test
    void deleteFirestations() {
        Firestations removeFirestations = new Firestations("1509 Culver St", "3");
        firestationsServiceImplProvider.deleteFirestations(removeFirestations);

        verify(firestationsServiceImplProvider, Mockito.times(1))
                .deleteFirestations(any(Firestations.class));
    }
}