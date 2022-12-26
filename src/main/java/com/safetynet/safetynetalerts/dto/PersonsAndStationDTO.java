package com.safetynet.safetynetalerts.dto;

import com.jsoniter.any.Any;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonsAndStationDTO {
    private String lastname;
    private String phone;
    private int age;
    private String medication;
    private String allergies;
    private String stationNumber;
}