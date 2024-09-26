package com.openclassrooms.projectJava9.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document(collation = "patients")
public class Patient {

    @Id
    private String id;
    @Indexed(unique = true)
    private String Nom;
    private  String Prenom;
    private Date Date_de_naissance;
    private String Genre;
    private String Adresse;
    private String Telephone;
}
