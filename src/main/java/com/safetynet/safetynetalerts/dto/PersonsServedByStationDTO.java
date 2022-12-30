package com.safetynet.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonsServedByStationDTO {
    private String lastname;
    private String phone;
    private int age;
    private String station;
    private List<String> medication;
    private List<String> allergies;
}
