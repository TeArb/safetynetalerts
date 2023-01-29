package com.safetynet.safetynetalerts.repositories;

import com.safetynet.safetynetalerts.models.Firestations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IFirestationsRepository {
    @Autowired
    private FirestationsRepository firestationsRepository;

    public void setup() {
        FirestationsRepository firestationsRepository = this.firestationsRepository;
    }

    public List<Firestations> getFirestations() {
        return firestationsRepository.getFirestations();
    }

    public Firestations addFirestations(Firestations newFirestations) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        firestationsList.add(newFirestations);
        return newFirestations;
    }

    public Firestations updateFirestations(Firestations oldFirestations, Firestations newFirestations) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();

            firestationsList.forEach(firestation -> {
                if (firestation.getStation().equals(oldFirestations.getStation())
                        && firestation.getAddress().equals(oldFirestations.getAddress())) {
                    firestation.setStation(newFirestations.getStation());
                    firestation.setAddress(newFirestations.getAddress());
                }
            });
        return newFirestations;
    }

    public String deleteFirestations(Firestations removeFirestations) {
        List<Firestations> firestationsList = firestationsRepository.getFirestations();
        int index = firestationsList.indexOf(removeFirestations);
        if (index > -1) {
            firestationsList.remove(removeFirestations);
            return "Object remove";
        }
        return "Object not found";
    }
}
