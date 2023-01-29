package com.safetynet.safetynetalerts.dto;

import com.safetynet.safetynetalerts.models.Firestations;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Contains constructor for initialize the args of "OldNewFirestations".
 *
 * @author Terry
 */
@Data
@AllArgsConstructor
public class OldAndNewFirestationsDTO {
    Firestations oldFirestations;
    Firestations newFirestations;
}
