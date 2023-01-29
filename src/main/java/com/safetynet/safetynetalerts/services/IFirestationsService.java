package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.models.Firestations;

import java.util.List;

public interface IFirestationsService {
    List<Firestations> getFirestations();
    Firestations addFirestations(Firestations newFirestations);
    Firestations updateFirestations (Firestations oldFirestations, Firestations newFirestations);
    String deleteFirestations(Firestations removeFirestations);
}
