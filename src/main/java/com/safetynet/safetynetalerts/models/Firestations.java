package com.safetynet.safetynetalerts.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Firestations {
    private String address;
    private String station;

    @Override
    public String toString() {
        return "Firestations{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }
}