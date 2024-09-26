package com.openclassrooms.projectJava9.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "patients")
public class Patient {

    @Id
    private String id;
    //@Indexed(unique = true)
    private String nom;
    private  String prenom;
    private Date date_de_naissance;
    private String genre;
    private String adresse;
    private String telephone;
}
