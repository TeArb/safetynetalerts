package com.safetynet.safetynetalerts.services;

import com.safetynet.safetynetalerts.models.FireStations;

import java.util.List;
/**
 * Contains abstract method get/post/put/delete of fire stations service.
 *
 * @author Terry
 */
public interface IFireStationsService {
    List<FireStations> getFireStations();
    List<FireStations> addFireStations(FireStations newFireStations);
    List<FireStations> updateFireStations(FireStations newFireStations, String address);
    String deleteFireStations(FireStations removeFireStations);
}
