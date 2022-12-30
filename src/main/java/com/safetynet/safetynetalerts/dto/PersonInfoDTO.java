package com.safetynet.safetynetalerts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonInfoDTO {
    private String lastname;
    private String address;
    private int age;
    private String email;
    private List<String> medication;
    private List<String> allergies;
}
