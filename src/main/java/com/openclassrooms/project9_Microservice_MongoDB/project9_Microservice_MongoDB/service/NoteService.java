package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // CRUD CREATE READ UPDATE DELETE
    public Note addNote(Note Note){
        Note.setId(UUID.randomUUID().toString().split("-")[0]);
        return noteRepository.save(Note);
    }
    public List<Note> findAllNote(){
        return noteRepository.findAll();
    }
    public Note getNoteById(String id){
        return noteRepository.findById(id).get();
    }
    public Note updateNote(Note noteRequest){

        Note existingNote = noteRepository.findById(noteRequest.getId()).get();

        existingNote.setHemoglobine(noteRequest.getHemoglobine());
        existingNote.setMicroalbumine(noteRequest.getMicroalbumine());
        existingNote.setTaille(noteRequest.getTaille());
        existingNote.setPoids(noteRequest.getPoids());
        existingNote.setFumeur(noteRequest.getFumeur());
        existingNote.setAnormal(noteRequest.getAnormal());
        existingNote.setCholesterol(noteRequest.getCholesterol());
        existingNote.setVertiges(noteRequest.getVertiges());
        existingNote.setRechute(noteRequest.getRechute());
        existingNote.setReaction(noteRequest.getReaction());
        existingNote.setAnticorps(noteRequest.getAnticorps());

        return  noteRepository.save(existingNote);
    }

    public String deleteNote(String NoteId){

        noteRepository.deleteById(NoteId);
        return NoteId+" Note deleted from table";
    }
}
