package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.repository;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note,String> {}
