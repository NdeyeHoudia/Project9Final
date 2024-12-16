package com.openclassrooms.projectJava9.controller;

import com.openclassrooms.projectJava9.model.NoteDTO;
import com.openclassrooms.projectJava9.model.Patient;
import com.openclassrooms.projectJava9.model.PatientDTO;
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
        model.addAttribute("patients", patientService.getAllPatient());
        return "patient/list";
    }

    @GetMapping("/patient/add")
    public String addPatientForm(PatientDTO patientDTO) {
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(@Validated PatientDTO patientDTO,
                           BindingResult result, Model model) {

        if(result.hasErrors()){
            return "patient/add";
        }

       patientService.addPatientDTO(patientDTO);
        model.addAttribute("patients", patientService.getAllPatient());
        return "patient/list";
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        PatientDTO patientDTO = patientService.getPatientId(id);
        model.addAttribute("patientDTO", patientDTO);
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id,
                                @Validated PatientDTO patientDTO,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "patient/update";
        }
        patientDTO.setId(id);
      //  patientService.addPatient(patient);
        patientService.updatePatientDTO(patientDTO);
        model.addAttribute("patients", patientService.getAllPatient());
        return "redirect:/patient/list";
    }


    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id, Model model) {

        PatientDTO patientDTO = patientService.getPatientId(id);
        patientService.deletePatient(id);
        model.addAttribute("patients", patientService.getAllPatient());
        return "redirect:/patient/list";
    }


    @GetMapping("/patient/history/{id}")
    public String consulPatient(Model model,@PathVariable("id") Integer id,
                                @Validated NoteDTO noteDTO)
    {

        PatientDTO patientDTO = patientService.getPatientId(id);
        List<NoteDTO> noteDTOS = noteService.listNoteById(String.valueOf(id));
      //  String status= patientService.statusPatient(id);

        String risque = patientService.risquePatient(id);

        model.addAttribute("patientDTO", patientDTO);
        model.addAttribute("noteDTOS", noteDTOS);
        model.addAttribute("risque", risque);


        return "patient/history";
    }

    @PostMapping("/patient/history/{id}")
    public String updateNotePatient(@PathVariable("id") Integer id,
                                    @Validated NoteDTO noteDTO,
                                    Model model,BindingResult result) {

        if(result.hasErrors()){
            return "note/add";
        }
        PatientDTO patientDTO = patientService.getPatientId(id);
        model.addAttribute("patientDTO", patientDTO);

        patientService.addNotePatient(noteDTO,id,patientDTO.getLastname());

        String risque = patientService.risquePatient(id);
        List<NoteDTO> noteDTOS = noteService.listNoteById(String.valueOf(id));

        model.addAttribute("noteDTOS", noteDTOS);
        model.addAttribute("risque", risque);

        return "patient/history";
    }

}
