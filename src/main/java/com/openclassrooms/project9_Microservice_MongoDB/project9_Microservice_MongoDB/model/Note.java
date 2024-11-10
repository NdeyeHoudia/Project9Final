package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Note {
    @Id
    private String id;
    private String note;
    @Field("patient_id")
    private String idPatient;
    @Field("patient")
    private String patient;

}
