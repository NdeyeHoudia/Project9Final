package com.openclassrooms.projectJava9.service;

import com.openclassrooms.projectJava9.model.NoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private static final  String BASE_URL = "http://localhost:8083/notes/all";

    @Autowired
    private NoteClient noteClient;
    public List<NoteDTO> getNoteDTOS(){
        RestTemplate restTemplate = new RestTemplate();
        NoteDTO[] notes = restTemplate.getForObject(BASE_URL, NoteDTO[].class);
        return List.of(notes);
    }
    public List<NoteDTO> listNoteById(String idPatient){
        List<NoteDTO> noteDTOS = getNoteDTOS();
        List<NoteDTO> noteDTOList = new ArrayList<>();

        for(NoteDTO noteDTO: noteDTOS){
            if(noteDTO.getIdPatient().equals(idPatient)){
                noteDTOList.add(noteDTO);
            }
        }
        return noteDTOList;
    }

}
