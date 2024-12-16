package com.openclassrooms.projectJava9.service;

import com.openclassrooms.projectJava9.model.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="backend-patient", url="http://localhost:8084/patients")

public interface PatientClient {

    @GetMapping("/all")
    public List<PatientDTO> getListPatient();
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO createPatient(@RequestBody PatientDTO patient);

    @GetMapping("/{id}")
    public PatientDTO patientById(@PathVariable Integer id);
    @PutMapping
    public PatientDTO updatePatient(@RequestBody PatientDTO patient);
    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Integer id);


}
