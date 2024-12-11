package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.service;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.KeyWord;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.PatientDTO;
import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class PatientService {

    @Autowired
    private NoteRepository noteRepository;
    private static final String BASE_URL = "http://localhost:8084/patients/all";

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

    public List<PatientDTO> getPatientDTOS() {
        RestTemplate restTemplate = new RestTemplate();
        PatientDTO[] notes = restTemplate.getForObject(BASE_URL, PatientDTO[].class);
        // assert notes != null;
        return List.of(notes);
    }

    public PatientDTO getPatientById(String id) {
        RestTemplate template = new RestTemplate();
        List<PatientDTO> patientDTOS = getPatientDTOS();

        try {
            //   String uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL).pathSegment(id).toUriString();
             //  return  template.getForObject(uriComponentsBuilder,PatientDTO.class);
            return patientDTOS.get(Integer.parseUnsignedInt(id));
        } catch (HttpClientErrorException e) {
            System.out.println("erreur de l'appel de l'api " + e.getStatusCode());
            return null;
        }
    }

    private String dateDeNaissance(String id) {
        PatientDTO patientDTO = getPatientById(id);
        if (patientDTO != null) {
            return patientDTO.getDate_of_birth();
        } else return "le patient non retrouvé pour" + id;
    }

    private String genre(String id) {
        PatientDTO patientDTO = getPatientById(id);
        if (patientDTO != null) {
            return patientDTO.getGenre();
        } else return "le patient non retrouvé pour" + id;
    }

    public int agePatient(String id) {

        LocalDate dateNaissance = LocalDate.parse(dateDeNaissance(id), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public String statusNotePatient(Integer idPatient) {

        if (agePatient(String.valueOf(idPatient)) < 30) {
            if (Objects.equals(genre(String.valueOf(idPatient)), "F")) {
                if ((countKeywordOccurrences(String.valueOf(idPatient), KeyWord.keyWord) == 4)) {
                    return "le patiente est en danger In Danger)";
                } else if (countKeywordOccurrences(String.valueOf(idPatient), KeyWord.keyWord) >= 7) {
                    return "la patient présente une apparition précoce (Early onset)";
                }
            } else if (Objects.equals(genre(String.valueOf(idPatient)), "M")) {
                if (countKeywordOccurrences(String.valueOf(idPatient), KeyWord.keyWord) == 3) {
                    return "le patient est en danger (In Danger)";
                } else if (countKeywordOccurrences(String.valueOf(idPatient), KeyWord.keyWord) >= 5) {
                    return "le patient présent une apparition précoce (Early onset)";
                }
            }
        }else if (agePatient(String.valueOf(idPatient)) > 30) {
            if (countKeywordOccurrences(String.valueOf(idPatient), KeyWord.keyWord) == 6 ||
                    countKeywordOccurrences(String.valueOf(idPatient), KeyWord.keyWord) == 7) {
                return "La personne est en danger";
            } else if (countKeywordOccurrences(String.valueOf(idPatient), KeyWord.keyWord) >= 8) {
                return " Le patient présente une apparition précoce";
            }
        }
        if (countKeywordOccurrences(String.valueOf(idPatient), KeyWord.keyWord) > 2
                && countKeywordOccurrences(String.valueOf(idPatient), KeyWord.keyWord) < 5) {
                    return "Le risque est limité (Borderline)";
            } else  return "Le patient présente aucun risque (None)";
        }

}