package com.openclassrooms.Project9MicroservicePatient.controller;

import com.openclassrooms.Project9MicroservicePatient.model.Patient;
import com.openclassrooms.Project9MicroservicePatient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @GetMapping("/all")
    public List<Patient> getListPatient(){
        return patientService.findAllPatient();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }
    @GetMapping("/{id}")
    public Patient patientById(@PathVariable Integer id){return patientService.getPatientById(id);}
    @PutMapping
    public Patient updatePatient(@RequestBody Patient patient){return patientService.updatePatient(patient);}
    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Integer id){return patientService.deletePatient(id);}

    @GetMapping("/{id}/agePatient")
    public int agePatient(@PathVariable Integer id){
        return patientService.agePatient(id);
    }

    @GetMapping("/{id}/genre")
    public String genrePatient(@PathVariable Integer id){
        return patientService.genrePatient(id);
    }
}
