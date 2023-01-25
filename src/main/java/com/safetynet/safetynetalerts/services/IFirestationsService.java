package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.models.Firestations;
import org.springframework.stereotype.Component;

public interface IFirestationsService {
    Firestations addFirestations(Firestations newFirestations);
}
