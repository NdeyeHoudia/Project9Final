package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.KeyWord;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // CRUD CREATE READ UPDATE DELETE
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }
    public Note saveNote(Note note, String patientId, String patient) {
        note.setIdPatient(patientId);
        note.setPatient(patient);
        return noteRepository.save(note);
    }

    public List<Note> findAllNote() {
        return noteRepository.findAll();
    }

    public Note getNoteById(String id) {
        return noteRepository.findNoteByID(id);
    }
   public Note updateNote(Note noteRequest) {

      Note existingNote = noteRepository.findNoteByID(noteRequest.getId());

        existingNote.setNote(noteRequest.getNote());
        existingNote.setPatient(noteRequest.getPatient());
        existingNote.setIdPatient(noteRequest.getIdPatient());
        return noteRepository.save(existingNote);
    }

    public String deleteNote(String NoteId) {
        noteRepository.deleteById(NoteId);
        return NoteId + " Note deleted from table";
    }

    public long countByNoteAndContentRegex22(List<String> keywords) {
        String regex = String.join("|", keywords);
       return noteRepository.countByPatientIdAndContentRegex2(regex);
    }
}
