package com.openclassrooms.projectJava9.controller;

import com.openclassrooms.projectJava9.model.NoteDTO;
import com.openclassrooms.projectJava9.model.Patient;
import com.openclassrooms.projectJava9.service.NoteService;
import com.openclassrooms.projectJava9.service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotePatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private NoteService noteService;

    @RequestMapping("/note/note")
    public String getNotes(Model model, HttpServletRequest request){
        model.addAttribute("remoteUser", request.getRemoteUser());
        model.addAttribute("notes", noteService.getNoteDTOS());
        return "note/note";
    }

    @GetMapping("/note/add/{id}")
    public String addNotePatientForm(@PathVariable("id") Integer id,NoteDTO noteDTO, Model model) {
        List<NoteDTO> notes = noteService.getNoteDTOS();
        Patient patient = patientService.getPatientById(id);


        model.addAttribute("patient", patient);
        model.addAttribute("notes", notes);

        return "note/add";
    }

    @RequestMapping("/note/list")
    public String home(Model model, HttpServletRequest request)
    {
        model.addAttribute("remoteUser", request.getRemoteUser());
        model.addAttribute("notes", noteService.getNoteDTOS());
        return "note/list";
    }
}
