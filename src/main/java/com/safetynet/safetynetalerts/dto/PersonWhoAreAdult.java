package com.safetynet.safetynetalerts.dto;

import com.safetynet.safetynetalerts.models.Persons;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class PersonWhoAreAdult {
    private int childrenNumbers;
    private int adultNumbers;
    private List<Persons> personsList;
}
