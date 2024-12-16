package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model;

import lombok.Data;

@Data
public class PatientDTO {
    private Integer id;

    private String date_of_birth;
    private String genre;
    private String lastname;
    private String firstname;
    private String address;
    private String phone;
}
