package com.openclassrooms.Project9MicroservicePatient.repository;

import com.openclassrooms.Project9MicroservicePatient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
