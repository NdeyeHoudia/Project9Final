package com.openclassrooms.Project9MicroservicePatient.service;


import com.openclassrooms.Project9MicroservicePatient.model.Patient;
import com.openclassrooms.Project9MicroservicePatient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // CRUD CREATE READ UPDATE DELETE
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }
    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).get();
    }

    public Patient updatePatient(Patient patientRequest) {
        Patient existingPatient = patientRepository.findById(patientRequest.getId()).get();

        existingPatient.setLastname(patientRequest.getLastname());
        existingPatient.setFirstname(patientRequest.getFirstname());
        existingPatient.setDate_of_birth(patientRequest.getDate_of_birth());
        existingPatient.setGenre(patientRequest.getGenre());
        existingPatient.setAddress(patientRequest.getAddress());
        existingPatient.setPhone(patientRequest.getPhone());

        return patientRepository.save(existingPatient);
    }

  //  public void deletePatient(Patient patient) {patientRepository.delete(patient);}

    public  String deletePatient(Integer patientId){
        patientRepository.deleteById(patientId);
        return patientId+" patient deleted from table";
    }

    public int agePatient(Integer id) {

        LocalDate dateNaissance = LocalDate.parse(getPatientById(id).getDate_of_birth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public String genrePatient(Integer id) {
        Patient patientById = getPatientById(id);
        if (patientById != null) {
            return patientById.getGenre();
        } else return "le patient non retrouvé pour" + id;
    }
}