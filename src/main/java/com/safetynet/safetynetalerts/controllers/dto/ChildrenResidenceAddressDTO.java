package com.safetynet.safetynetalerts.controllers.dto;

import com.safetynet.safetynetalerts.models.Persons;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.util.List;
/**
 * Contains constructor for initialize the args of "ChildrenResidenceAddressDTO".
 *
 * @author Terry
 */
@Data
@AllArgsConstructor
@Generated
public class ChildrenResidenceAddressDTO {
    private String childrenFirstName;
    private String childrenLastName;
    private int childrenAge;
    private List<Persons> householdMembers;
}