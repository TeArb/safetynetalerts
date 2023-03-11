package com.safetynet.safetynetalerts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Contains constructor and  method to get/set a fire station's info.
 *
 * @author Terry
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class FireStations {
    private String address;
    private String station;

    @Override
    public String toString() {
        return "FireStations{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }
}