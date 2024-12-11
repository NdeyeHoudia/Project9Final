package com.openclassrooms.projectJava9.service;

import com.openclassrooms.projectJava9.model.NoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@FeignClient(name="${feign.client}")

@FeignClient(name="${feign.client}", url="http://localhost:8083/notePatient")


public interface NoteClient {

    @GetMapping("/{id}/status")
    public String statusNotePatient(@PathVariable Integer id);


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDTO createNote(@RequestBody NoteDTO note,
                           @RequestParam(value = "patientId", required = true) String patientId,
                           @RequestParam String patient);
}
