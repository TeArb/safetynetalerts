package com.safetynet.safetynetalerts.dto;

import com.jsoniter.any.Any;
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
    private List<Any> medication;
    private List<Any> allergies;
}
