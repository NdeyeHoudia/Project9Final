package com.openclassrooms.projectJava9.controller;

import com.openclassrooms.projectJava9.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/all")
    public String home(Model model)
    {
        model.addAttribute("patients", patientService.getPatientLists());
        return "patient/list";
    }
}
