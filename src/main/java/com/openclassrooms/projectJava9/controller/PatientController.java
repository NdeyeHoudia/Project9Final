package com.openclassrooms.projectJava9.controller;

import com.openclassrooms.projectJava9.model.Patient;
import com.openclassrooms.projectJava9.repository.PatientRepository;
import com.openclassrooms.projectJava9.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    @GetMapping("/all")
    public List<Patient> getPatient(){
        return patientService.findAllPatient();
    }

    @GetMapping("{patientId}")
    public Patient getPatient(@PathVariable String patientId){
        return patientService.getPatientById(patientId);
    }

    @PutMapping
    public Patient modifyPatient(@RequestBody Patient patient){
      return patientService.updatePatient(patient);
    }

    @DeleteMapping("/{patientId}")
    public String deletePatient(@PathVariable String patientId){
      return patientService.deletePatient(patientId);
    }
}
