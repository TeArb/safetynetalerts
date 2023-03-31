package com.safetynet.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.util.List;
/**
 * Contains constructor for initialize the args of "ResidentAddressAndStationNumberDTO".
 *
 * @author Terry
 */
@Data
@AllArgsConstructor
@Generated
public class ResidentAddressAndStationNumberDTO {
    private String lastName;
    private String phone;
    private int age;
    private List<String> medication;
    private List<String> allergies;
    private String stationNumber;
}