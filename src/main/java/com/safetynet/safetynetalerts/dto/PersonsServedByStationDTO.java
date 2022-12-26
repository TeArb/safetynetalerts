package com.safetynet.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonsServedByStationDTO {
    private String lastname;
    private String phone;
    private int age;
    private String medication;
    private String allergies;
}
