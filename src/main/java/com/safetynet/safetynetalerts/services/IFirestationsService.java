package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.models.Firestations;

import java.util.List;
/**
 * Contains abstract method get/post/put/delete of fire stations service.
 *
 * @author Terry
 */
public interface IFirestationsService {
    List<Firestations> getFirestations();
    List<Firestations> addFirestations(Firestations newFirestations);
    List<Firestations> updateFirestations (Firestations NewFirestations, String address);
    String deleteFirestations(Firestations removeFirestations);
}
