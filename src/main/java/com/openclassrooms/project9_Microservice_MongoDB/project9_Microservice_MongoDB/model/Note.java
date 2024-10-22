package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "notes")

public class Note {
    @Id
    private String id;
    //@Indexed(unique = true)
    private String hemoglobine;
    private String microalbumine;
    private String taille;
    private String poids;
    private String fumeur;
    private String anormal;
    private String cholesterol;
    private String vertiges;
    private String rechute;
    private String reaction;
    private String anticorps;

    private String idPatient;

}
