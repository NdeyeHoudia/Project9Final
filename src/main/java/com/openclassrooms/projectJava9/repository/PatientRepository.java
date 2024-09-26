package com.openclassrooms.projectJava9.repository;

import com.openclassrooms.projectJava9.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
}
