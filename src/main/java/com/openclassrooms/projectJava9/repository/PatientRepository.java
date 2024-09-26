package com.openclassrooms.projectJava9.repository;

import com.openclassrooms.projectJava9.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
}
