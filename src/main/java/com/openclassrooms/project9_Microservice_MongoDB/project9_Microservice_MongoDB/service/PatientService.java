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
    private static final  String BASE_URL = "http://localhost:8084/patients/all";


    public  int countKeywordOccurrences(String id, String[] keywords) {
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

    // déterminer si le patient a le diabete

    public List<PatientDTO> getPatientDTOS(){
        RestTemplate restTemplate = new RestTemplate();
        PatientDTO[] notes = restTemplate.getForObject(BASE_URL, PatientDTO[].class);
       // assert notes != null;
        return List.of(notes);
    }
    public PatientDTO getPatientById(String  id){
        RestTemplate template =new RestTemplate();
        List<PatientDTO> patientDTOS = getPatientDTOS();

        try {
        //    String uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL).pathSegment(id).toUriString();
         //   return  template.getForObject(uriComponentsBuilder,PatientDTO.class);
            return patientDTOS.get(Integer.parseInt(id));
        }catch (HttpClientErrorException e){
            System.out.println("erreur de l'appel de l'api "+ e.getStatusCode());
            return null;
        }
    }
    private String dateDeNaissance(String id) {
        PatientDTO patientDTO = getPatientById(id);
       // List<PatientDTO> patientDTOS = getPatientDTOS();
        if (patientDTO!=null) {
         //   patientDTOS.get(id).getDate_of_birth();
            return patientDTO.getDate_of_birth();
        }else return  "le patient non retrouvé pour" + id;
    }

    private String genre(String id){
        PatientDTO patientDTO = getPatientById(id);
        if(patientDTO != null){
           return patientDTO.getGenre();
        } else return  "le patient non retrouvé pour" + id;
}
    public int agePatient(String id){

        LocalDate dateNaissance = LocalDate.parse(dateDeNaissance(id), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
    public String patientZeroRisk(String note){

        if(!(note.contains(KeyWord.microalbumine) || note.contains(KeyWord.hemoglobine) ||
                note.contains(KeyWord.taille) || note.contains(KeyWord.poids) ||
                note.contains(KeyWord.fumeur) || note.contains(KeyWord.anormal) ||
                note.contains(KeyWord.cholesterol) || note.contains(KeyWord.vertiges) ||
                note.contains(KeyWord.rechute) || note.contains(KeyWord.reaction) ||
                note.contains(KeyWord.anticorps) || note.contains(KeyWord.stress))){
            return "aucun risque";
        }else return "le patient présente des risque";
    }
    public String patientBorderline(String idPatient, String[] keywords, String birthdate){

        if(countKeywordOccurrences(idPatient, keywords) > 2
                && countKeywordOccurrences(idPatient, keywords) <5
                && agePatient(birthdate) >30){
            return "risque limité";
        }else return " le patient présente beaucoup de risques";
    }

    public String patientDanger(String idPatient, String[] keywords,String birthdate){
        if(Objects.equals(genre(birthdate), "F")
                && (agePatient(birthdate) < 30)
                && (countKeywordOccurrences(idPatient, keywords) == 3)){
            return "la patiente est en danger";
        }
        else if(Objects.equals(genre(birthdate), "M")
                && (agePatient(birthdate) < 30)
                && (countKeywordOccurrences(idPatient, keywords) == 4)){
            return "le patient est en danger";
        }else return "le patient n'est pas en dangers";
    }
    public String earlyOnset(String idPatient, String[] keywords,String birthdate) {
        if (Objects.equals(genre(birthdate), "M")
                && (agePatient(birthdate) < 30)
                && (countKeywordOccurrences(idPatient, keywords) >= 5)) {
            return "le patient présent d'apparition précoce";
        } else if (Objects.equals(genre(birthdate), "F")
                && (agePatient(birthdate) < 30)
                && (countKeywordOccurrences(idPatient, keywords) >= 7)) {
            return "la patient présente d'apparition précoce";
        } else if ((agePatient(birthdate) > 30)
                && (countKeywordOccurrences(idPatient, keywords) <= 8)) {
            return "on constate une apparition précoce";
        }else return "ancune apparition précoce" ;
    }
    public String statusNotePatient(String idPatient, String[] keywords,String birthdate){
        if(Objects.equals(genre(birthdate), "F") && (agePatient(birthdate) < 30))
        {
                if((countKeywordOccurrences(idPatient, keywords) == 3)){
                    return "la patiente est en danger";
                } else if(countKeywordOccurrences(idPatient, keywords) >= 7){
                    return "la patient présente une apparition précoce";
                }
        }else if(Objects.equals(genre(birthdate), "M") && (agePatient(birthdate) < 30)){
                if(countKeywordOccurrences(idPatient, keywords) == 4){
                    return "le patient est en danger";
                } else if (countKeywordOccurrences(idPatient, keywords) >= 5) {
                    return "le patient présent une apparition précoce";
                }
        }else if (agePatient(birthdate) > 30){
                if(countKeywordOccurrences(idPatient, keywords) >= 8){
                    return "on constate une apparition précoce";
                } else if (countKeywordOccurrences(idPatient, keywords) > 2
                        && countKeywordOccurrences(idPatient, keywords) <5) {
                    return "risque limité";
                }else return "aucun risque";
        } return "ancune apparition précoce" ;
    }
}