package com.safetynet.safetynetalerts.servicesImplement;

import com.safetynet.safetynetalerts.models.Medicalrecords;
import com.safetynet.safetynetalerts.repositories.MedicalrecordsServiceImplProvider;
import com.safetynet.safetynetalerts.services.IMedicalrecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalrecordsServiceImpl implements IMedicalrecordsService {
    @Autowired
    private MedicalrecordsServiceImplProvider medicalrecordsServiceImplProvider;
    private static List<Medicalrecords> medicalrecordsList;

    /**
     * Set static the medical records list.
     */
    public void setUp() {
        medicalrecordsList = medicalrecordsServiceImplProvider.getMedicalrecords();
    }

    /**
     * Get the method to get a medical records list.
     */
    public List<Medicalrecords> getMedicalrecords() {
        setUp();
        return medicalrecordsList;
    }

    /**
     * Get the method to add a medical records list.
     */
    public List<Medicalrecords> addMedicalrecords(Medicalrecords newMedicalrecords) {
        setUp();
        medicalrecordsList.add(medicalrecordsServiceImplProvider.addMedicalrecords(newMedicalrecords));

        return medicalrecordsList;
    }

    /**
     * Get the method to update a medical records.
     */
    @Override
    public List<Medicalrecords> updateMedicalrecords(Medicalrecords newMedicalrecords, String firstName, String lastName) {
        setUp();
        medicalrecordsList.remove(medicalrecordsList.stream()
                .filter(medicalrecords-> medicalrecords.getFirstName().equals(firstName)
                        && medicalrecords.getLastName().equals(lastName))
                .findFirst().orElse(null));
        medicalrecordsList.add(this.medicalrecordsServiceImplProvider
                .updateMedicalrecords(newMedicalrecords, firstName, lastName));

        return medicalrecordsList;
    }

    /**
     * Get the method to delete a medical records.
     */
    @Override
    public String deleteMedicalrecords(Medicalrecords removeMedicalrecords, String firstName, String lastName) {
        return this.medicalrecordsServiceImplProvider.deleteMedicalrecords(removeMedicalrecords, firstName, lastName);
    }
}