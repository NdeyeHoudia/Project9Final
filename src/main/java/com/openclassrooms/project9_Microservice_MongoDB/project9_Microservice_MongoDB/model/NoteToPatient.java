package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "notesPatient")
public class NoteToPatient {

    private Note note;
    private String idPatient;
}
