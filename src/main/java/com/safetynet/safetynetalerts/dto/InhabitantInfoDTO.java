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
public class InhabitantInfoDTO {
    private String lastname;
    private String address;
    private int age;
    private String email;
    private List<String> medication;
    private List<String> allergies;
}
