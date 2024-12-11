package com.openclassrooms.projectJava9.repository;

import com.openclassrooms.projectJava9.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByLastname(String lastname);
}
