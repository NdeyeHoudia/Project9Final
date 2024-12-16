package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private NoteRepository noteRepository;

    public int countKeywordOccurrences(String id,  String[] keywords) {
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
    }

}