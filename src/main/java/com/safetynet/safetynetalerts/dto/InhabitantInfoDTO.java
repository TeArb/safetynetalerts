package com.safetynet.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.util.List;
/**
 * Contains constructor for initialize the args of "InhabitantInfoDTO".
 *
 * @author Terry
 */
@Data
@AllArgsConstructor
@Generated
public class InhabitantInfoDTO {
    private String lastName;
    private String address;
    private int age;
    private String email;
    private List<String> medication;
    private List<String> allergies;
}
