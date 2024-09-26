package com.openclassrooms.projectJava9.service;

import com.openclassrooms.projectJava9.model.Patient;
import com.openclassrooms.projectJava9.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

   @Autowired
    private PatientRepository patientRepository;

    // CRUD CREATE READ UPDATE DELETE
    public Patient addPatient(Patient patient){
        patient.setId(UUID.randomUUID().toString().split("-")[0]);
       return patientRepository.save(patient);
    }
    public List<Patient> findAllPatient(){
        return patientRepository.findAll();
    }
    public Patient getPatientById(String id){
        return patientRepository.findById(id).get();
    }
    public Patient updatePatient(Patient patientRequest){
        Patient existingPatient = patientRepository.findById(patientRequest.getId()).get();

        existingPatient.setNom(patientRequest.getNom());
        existingPatient.setPrenom(patientRequest.getPrenom());
        existingPatient.setDate_de_naissance(patientRequest.getDate_de_naissance());
        existingPatient.setGenre(patientRequest.getGenre());
        existingPatient.setAdresse(patientRequest.getAdresse());
        existingPatient.setTelephone(patientRequest.getTelephone());

        return  patientRepository.save(existingPatient);
    }

    public  String deletePatient(String patientId){
        patientRepository.deleteById(patientId);
        return patientId+" patient deleted from table";
    }
}
