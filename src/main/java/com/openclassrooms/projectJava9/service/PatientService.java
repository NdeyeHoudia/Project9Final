package com.openclassrooms.projectJava9.service;

import com.openclassrooms.projectJava9.model.NoteDTO;
import com.openclassrooms.projectJava9.model.Patient;
import com.openclassrooms.projectJava9.model.PatientDTO;
import com.openclassrooms.projectJava9.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private NoteClient noteClient;
    @Autowired
    private PatientClient patientClient;


    @Autowired
    private RisqueClient risqueClient;

    /*public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).get();
    }

    public Patient updatePatient(Patient patientRequest) {
        Patient existingPatient = patientRepository.findById(patientRequest.getId()).get();

        existingPatient.setLastname(patientRequest.getLastname());
        existingPatient.setFirstname(patientRequest.getFirstname());
        existingPatient.setDate_of_birth(patientRequest.getDate_of_birth());
        existingPatient.setGenre(patientRequest.getGenre());
        existingPatient.setAddress(patientRequest.getAddress());
        existingPatient.setPhone(patientRequest.getPhone());

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

     */

   /* public  String statusPatient(Integer idPa){
        Integer existingPatient = patientRepository.findById(idPa).get().getId();
        return noteClient.statusNotePatient(existingPatient);
    }*/

   public NoteDTO addNotePatient(NoteDTO noteDTO,Integer idPatient,String patient){
        Integer existingPatient = patientRepository.findById(idPatient).get().getId();
        Optional<Patient> patient1 = patientRepository.findByLastname(patient);
        String lastname = patient1.get().getLastname();
        return noteClient.createNote(noteDTO, String.valueOf(existingPatient),lastname);

    }
    public List<PatientDTO> getAllPatient(){
        return patientClient.getListPatient();
    }
    public PatientDTO addPatientDTO(PatientDTO patientDTO){
        return patientClient.createPatient(patientDTO);
    }

    public PatientDTO getPatientId(Integer idPatient){
        return patientClient.patientById(idPatient);
    }
    public PatientDTO updatePatientDTO(PatientDTO  patientDTO){
        return patientClient.updatePatient(patientDTO);
    }
    public String deletePatient(Integer id){
        return patientClient.deletePatient(id);
    }

    public String risquePatient(Integer id){
        return risqueClient.statusPatient(id);
    }
}