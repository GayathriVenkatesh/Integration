package net.javaguides.springboot.service;
import java.time.LocalDate;
import java.util.List;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Patient;
import net.javaguides.springboot.repository.PatientRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public List<Patient> getPatients() {
        // if (keyword != null) {
        //     System.out.println("KEYWORD " + keyword);
        //     return this.patientRepository.search(keyword);
        // }
        // return this.patientRepository.findByName("Aruna");
        return this.patientRepository.findAll();
    }

    public List<Patient> getByKeyword(String name, String address, String religion, String samId, String sam){
        return this.patientRepository.findByKeyword(name, address, religion, samId, sam);
    }

    public Patient getPatientById(Long samId) {
        Patient p = this.patientRepository.findBySamId(samId).orElseThrow(
            () -> new ResourceNotFoundException("No Patient with given SAM ID")
        );
        System.out.println("NEW PATIENT " + p.getName() + " " + p.getSamId() + " " + p.getContact_no());
        return p;
    }

    public List<String> getStringPatients() {
        List<Patient> p = this.patientRepository.findAll();
        List<String> myList = List.of(); 
        for(Patient patient : p) {
            String s = patient.toString();
            myList.add(s);
        }
        return myList;
    }

    @Transactional
    public void updatePatient(Long samId, String name, LocalDate dob, Long uhId,
                Long rchId, String contactNumber, Character gender, Boolean BPL, 
                String addr, String religion, String caste, String relationshipStatus, 
                String symptoms, String refer) {

        Patient p = patientRepository.findBySamId(samId)
            .orElseThrow(() -> new IllegalStateException(
                "Patient with SAM ID " + samId + " does not exist"
            ));
        
        if (name != null && name.length() > 0 && !name.equals(p.getName())) { p.setName(name); }
        if (dob != null && !dob.equals(p.getDob())) { p.setDob(dob); }
        if (uhId != null && uhId != p.getUhid()) {  p.setUhid(uhId); }
        if (rchId != null && rchId != p.getRch_id()) {  p.setRch_id(rchId); }
        if (contactNumber != null && contactNumber.length() > 0 && !contactNumber.equals(p.getContact_no())) { p.setContact_no(contactNumber); }
        if (religion != null && religion.length() > 0 && !religion.equals(p.getReligion())) {
            p.setReligion(religion);
        }
        if (caste != null && caste.length() > 0 && !caste.equals(p.getCaste())) {
            p.setCaste(caste);
        }

        if (relationshipStatus != null && relationshipStatus.length() > 0 && !relationshipStatus.equals(p.getRelationship())) {
            p.setRelationship(relationshipStatus);
        }
        if (symptoms != null && symptoms.length() > 0 && !symptoms.equals(p.getSymptoms())) {
            p.setSymptoms(symptoms);
        }
        if (refer != null && refer.length() > 0 && !refer.equals(p.getReferred_by())) {
            p.setReferred_by(refer);
        }

        if (gender != null && gender != p.getGender()) {  p.setGender(gender); }
        if (BPL != null && BPL != p.getBpl()) {  p.setBpl(BPL); }
        // if (health != null && !health.equals(p.getHealth_params())) {
        //     p.setHealth_params(health);
        // }
    }

    public void addPatient(Patient p) {
        patientRepository.save(p);
    }
}
