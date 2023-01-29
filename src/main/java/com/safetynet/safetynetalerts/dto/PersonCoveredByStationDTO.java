package com.safetynet.safetynetalerts.dto;

import com.safetynet.safetynetalerts.models.Persons;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
/**
 * Contains constructor for initialize the args of "PersonCoveredByStationDTO".
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