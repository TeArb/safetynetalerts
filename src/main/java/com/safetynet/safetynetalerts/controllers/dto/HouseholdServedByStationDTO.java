package com.safetynet.safetynetalerts.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.util.List;
/**
 * Contains constructor for initialize the args of "HouseholdServedByStationDTO".
 *
 * @author Terry
 */
@Data
@AllArgsConstructor
@Generated
public class HouseholdServedByStationDTO {
    private String lastName;
    private String phone;
    private int age;
    private List<String> medication;
    private List<String> allergies;
}
