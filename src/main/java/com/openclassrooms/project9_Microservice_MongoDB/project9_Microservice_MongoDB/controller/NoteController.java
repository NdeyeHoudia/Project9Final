package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.controller;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    
    @Autowired
    private NoteService noteService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody Note Note){
        return noteService.addNote(Note);
    }

    @GetMapping("/all")
    public List<Note> getNote(){
        return noteService.findAllNote();
    }

    @GetMapping("{NoteId}")
    public Note getNote(@PathVariable String NoteId){
        return noteService.getNoteById(NoteId);
    }

    @PutMapping
    public Note modifyNote(@RequestBody Note Note){
      return noteService.updateNote(Note);
    }
   @DeleteMapping("/{NoteId}")
    public String deleteNote(@PathVariable String NoteId){
      return noteService.deleteNote(NoteId);
    }
}
