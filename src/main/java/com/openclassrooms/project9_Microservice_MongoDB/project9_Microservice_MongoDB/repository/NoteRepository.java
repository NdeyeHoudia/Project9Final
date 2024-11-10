package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.repository;

import com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note,String> {

  @Query(value = "{'patient_id': ?0 ,'note': {$regex: ?0}}",count = true)
  long countByPatientIdAndContentRegex(String patient_id,String keyword);

  @Query(value = "{'note' : {$regex: ?0}}", count = true)
  long countByPatientIdAndContentRegex2(String note);

  @Query(value = "{'patient_id': ?0}")
  Note findNoteByID(String patient_id);

  Optional<Note> findById(String id);

  List<Note> findByIdPatient(String idPatient);
}

