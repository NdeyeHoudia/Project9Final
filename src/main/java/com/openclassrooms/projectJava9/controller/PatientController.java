package com.openclassrooms.projectJava9.controller;

import com.openclassrooms.projectJava9.model.NoteDTO;
import com.openclassrooms.projectJava9.model.Patient;
import com.openclassrooms.projectJava9.service.NoteClient;
import com.openclassrooms.projectJava9.service.NoteService;
import com.openclassrooms.projectJava9.service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private NoteClient noteClient;

    @Autowired
    private NoteService noteService;
    @RequestMapping("/patient/list")
    public String home(Model model, HttpServletRequest request)
    {
        model.addAttribute("remoteUser", request.getRemoteUser());
        model.addAttribute("patients", patientService.findAllPatient());
        return "patient/list";
    }

    @GetMapping("/patient/add")
    public String addPatientForm(Patient patient) {
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(@Validated Patient patient,
                           BindingResult result, Model model) {

        if(result.hasErrors()){
            return "patient/add";
        }

       patientService.addPatient(patient);
        model.addAttribute("patients", patientService.findAllPatient());
        return "patient/list";
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id,
                                @Validated Patient patient,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "patient/update";
        }
        patient.setId(id);
      //  patientService.addPatient(patient);
        patientService.updatePatient(patient);
        model.addAttribute("patients", patientService.findAllPatient());
        return "redirect:/patient/list";
    }


    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id, Model model) {

        Patient patient = patientService.getPatientById(id);
        patientService.deletePatient(patient);
        model.addAttribute("patients", patientService.findAllPatient());
        return "redirect:/patient/list";
    }


    @GetMapping("/patient/history/{id}")
    public String consulPatient(Model model,@PathVariable("id") Integer id,
                                @Validated NoteDTO noteDTO)
    {

        Patient patient = patientService.getPatientById(id);
        List<NoteDTO> noteDTOS = noteService.listNoteById(String.valueOf(id));
      //  List<NoteDTO> listNoteByiD = noteService.addNoteDto(noteDTO);

        String status= patientService.statusPatient(id);

        model.addAttribute("patient", patient);
        model.addAttribute("noteDTOS", noteDTOS);
         model.addAttribute("status", status);

        return "patient/history";
    }

    @PostMapping("/patient/history/{id}")
    public String updateNotePatient(@PathVariable("id") Integer id,
                                    @Validated NoteDTO noteDTO,
                                    Model model,BindingResult result) {

        if(result.hasErrors()){
            return "note/add";
        }
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);

        patientService.addNotePatient(noteDTO,id,patient.getLastname());

        String status= patientService.statusPatient(id);
        List<NoteDTO> noteDTOS = noteService.listNoteById(String.valueOf(id));

        model.addAttribute("noteDTOS", noteDTOS);
        model.addAttribute("status", status);


        return "patient/history";
    }

}
