package com.openclassrooms.projectJava9.service;

import com.openclassrooms.projectJava9.model.Patient;
import com.openclassrooms.projectJava9.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // findAll
public List<Patient> getPatientLists(){
    return patientRepository.findAll();
}
}
