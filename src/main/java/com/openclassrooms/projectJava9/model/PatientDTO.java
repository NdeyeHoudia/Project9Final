package com.openclassrooms.projectJava9.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

public class PatientDTO {
    private Integer id;

    private String lastname;
    private String firstname;
    private String date_of_birth;
    private String genre;
    private String address;
    private String phone;

}
