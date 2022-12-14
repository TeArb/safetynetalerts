package com.safetynet.safetynetalerts.dto;

import com.safetynet.safetynetalerts.models.Persons;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChildrenResidenceAddressDTO {
    private String childrenFirstname;
    private String childrenLastname;
    private int childrenAge;
    private List<Persons> householdMembers;
}