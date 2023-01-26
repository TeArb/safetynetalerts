package com.safetynet.safetynetalerts.dto;

import com.safetynet.safetynetalerts.models.Persons;
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
public class PersonCoveredByStationDTO {
    private int childrenNumber;
    private int adultNumber;
    private List<Persons> personsList;
}