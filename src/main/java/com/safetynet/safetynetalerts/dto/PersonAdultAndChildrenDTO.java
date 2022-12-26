package com.safetynet.safetynetalerts.dto;

import com.safetynet.safetynetalerts.models.Persons;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class PersonAdultAndChildrenDTO {
    private int childrenNumber;
    private int adultNumber;
    private List<Persons> personsList;
}