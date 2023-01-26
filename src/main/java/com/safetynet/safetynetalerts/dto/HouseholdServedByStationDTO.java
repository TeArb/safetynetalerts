package com.safetynet.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
/**
 * Contains constructor to initialize the args of the class
 *
 * @author Terry
 */
@Data
@AllArgsConstructor
public class HouseholdServedByStationDTO {
    private String lastname;
    private String phone;
    private int age;
    private List<String> medication;
    private List<String> allergies;
}
