package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.controller;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.PatientDTO;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service.NoteService;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notePatient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/{id}/count-keyWord")
    public int countKeywordsInNote(
            @PathVariable String id,
            @RequestParam String[] keywords) {
        return patientService.countKeywordOccurrences(id, keywords);
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

    @GetMapping("/{id}/status")
    public String statusNotePatient(
            @PathVariable Integer id
    ) {
        return patientService.statusNotePatient(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note,
                           @RequestParam(value = "patientId", required = true) String patientId,
                           @RequestParam String patient){
        return noteService.saveNote(note,patientId,patient);
    }
}
