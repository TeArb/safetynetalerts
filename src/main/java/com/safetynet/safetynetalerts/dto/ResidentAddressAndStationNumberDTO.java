package com.safetynet.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResidentAddressAndStationNumberDTO {
    private String lastname;
    private String phone;
    private int age;
    private List<String> medication;
    private List<String> allergies;
    private String stationNumber;
}