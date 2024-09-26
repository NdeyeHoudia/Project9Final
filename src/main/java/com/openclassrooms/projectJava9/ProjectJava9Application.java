package com.openclassrooms.projectJava9;

import com.openclassrooms.projectJava9.model.Patient;
import com.openclassrooms.projectJava9.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class ProjectJava9Application implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(ProjectJava9Application.class);


	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectJava9Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<Patient> patient = patientRepository.findById("66f2dfcf7dbb43d16f803347");
			if(patient.isPresent()){
				logger.info(patient.get().getId());
			}else {
				logger.info("Patient not found");
			}
	}

}
