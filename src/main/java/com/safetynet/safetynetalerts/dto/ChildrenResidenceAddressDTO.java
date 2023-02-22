package com.safetynet.safetynetalerts.dto;

import com.safetynet.safetynetalerts.models.Persons;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
/**
 * Contains constructor for initialize the args of "ChildrenResidenceAddressDTO".
 *
 * @author Terry
 */
@Data
@AllArgsConstructor
public class ChildrenResidenceAddressDTO {
    private String childrenFirstName;
    private String childrenLastName;
    private int childrenAge;
    private List<Persons> householdMembers;
}