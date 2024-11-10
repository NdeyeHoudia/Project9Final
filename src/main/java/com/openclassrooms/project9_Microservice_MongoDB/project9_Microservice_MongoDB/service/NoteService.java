package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.KeyWord;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

    // déterminer si le patient a le diabete

    public boolean patientNone(Note note){

        return !(note.getNote().contains(KeyWord.microalbumine) || note.getNote().contains(KeyWord.hemoglobine) ||
                note.getNote().contains(KeyWord.taille) || note.getNote().contains(KeyWord.poids) ||
                note.getNote().contains(KeyWord.fumeur) || note.getNote().contains(KeyWord.anormal) ||
                note.getNote().contains(KeyWord.cholesterol) || note.getNote().contains(KeyWord.vertiges) ||
                note.getNote().contains(KeyWord.rechute) || note.getNote().contains(KeyWord.reaction) ||
                note.getNote().contains(KeyWord.anticorps) || note.getNote().contains(KeyWord.stress));
    }

    // Compter les occurrences des mots-clés dans toutes les notes du patient

    public int countKeywordOccurrences(String id, String[] keywords) {
        // Récupérer la note par ID
        List<Note> notes = noteRepository.findByIdPatient(id);
        if (notes.isEmpty()) {
            throw new IllegalArgumentException("Note not found with ID: " + id);
        }
// Compter les occurrences des mots-clés dans toutes les notes du patient
        int totalOccurrences = 0;
        for (Note note : notes) {
            String content = note.getNote();
            for (String keyword : keywords) {
                String keywordLower = keyword.toLowerCase();
                int keywordCount = content.split(keywordLower, -1).length - 1;
                totalOccurrences += keywordCount;
            }
        }
        return totalOccurrences;
        // Compter les occurrences des mots-clés
    }
}
