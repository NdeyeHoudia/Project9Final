package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.controller;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    
    @Autowired
    private NoteService noteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note,
                           @RequestParam(value = "patientId", required = true) String patientId,
                           @RequestParam String patient){
        return noteService.saveNote(note,patientId,patient);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>>  getNote(){
        return ResponseEntity.ok(noteService.findAllNote());
    }

    @GetMapping("/noteId")
    public Note getNote(@RequestParam String patient_id){
        return noteService.getNoteById(patient_id);
    }

   @PutMapping
    public Note modifyNote(@RequestBody Note note){return noteService.updateNote(note);}
   @DeleteMapping("/{noteId}")
    public String deleteNote(@PathVariable String noteId){return noteService.deleteNote(noteId);}
    @GetMapping("/countKeyWord")
    public long wordKey(@RequestParam List<String> keywords){return noteService.countByNoteAndContentRegex22(keywords);}

}
