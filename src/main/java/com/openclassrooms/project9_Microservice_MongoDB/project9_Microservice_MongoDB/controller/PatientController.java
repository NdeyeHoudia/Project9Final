package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.controller;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.PatientDTO;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notePatient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}/count-keyWord")
    public int countKeywordsInNote(
            @PathVariable String id,
            @RequestParam String[] keywords) {
        return patientService.countKeywordOccurrences(id, keywords);
    }

    @GetMapping("/patientNone")
    public String none(@RequestParam String note){return patientService.patientZeroRisk(note);}

    @GetMapping("/{id}/borderline")
    public String borderline(@RequestParam String birthdate,
                             @PathVariable String id,
                             @RequestParam String[] keywords
    ) {
        return patientService.patientBorderline(id,keywords,birthdate);
    }

    @GetMapping("/{id}/danger")
    public String danger(@RequestParam String birthdate,
                             @PathVariable String id,
                             @RequestParam String[] keywords
    ) {
        return patientService.patientDanger(id,keywords,birthdate);
    }

    @GetMapping("/age-patient")
    public int agePatient(@RequestParam String birthdate) {return patientService.agePatient(birthdate);
    }

    @GetMapping("/id-patient")
    public PatientDTO getPatientDTO(@RequestParam String birthdate) {
        return patientService.getPatientById(birthdate);
    }

    @GetMapping("/listPatient")
    public List<PatientDTO> getAllPatient() {
        return patientService.getPatientDTOS();
    }
}
