package net.javaguides.springboot.service;
import java.time.LocalDate;
import java.util.List;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.DischargeSummary;
import net.javaguides.springboot.model.Patient;
import net.javaguides.springboot.repository.DischargeSummaryRepository;
import net.javaguides.springboot.repository.PatientRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DischargeSummaryService {

    private final DischargeSummaryRepository dischargeRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DischargeSummaryService(DischargeSummaryRepository dischargeRepository, PatientRepository patientRepository) {
        this.dischargeRepository = dischargeRepository;
        this.patientRepository = patientRepository;
    }
    public List<DischargeSummary> getDischargeSummaries() {
        return this.dischargeRepository.findAll();
    }

    public List<DischargeSummary> getDischargeSummaryBySamId(Long samId) {
        Patient p = this.patientRepository.findBySamId(samId).orElseThrow(
            () -> new ResourceNotFoundException("No patient with given ID")
        );
        return p.getDischarge_summaries();
    }

    // public List<DischargeSummary> getByKeyword(String dischargeId, String name){
    //     return this.dischargeRepository.findByKeyword(dischargeId, name);
    // }

    public DischargeSummary getDischargeSummaryById(Long dischargeId) {
        DischargeSummary d = this.dischargeRepository.findByDischargeId(dischargeId).orElseThrow(
            () -> new ResourceNotFoundException("No summary with given ID")
        );
        return d;
    }

    @Transactional
    public void updateDischargeSummary(Long discharge_id, LocalDate admissionDate,
    LocalDate dischargeDate, Double admissionWeight, Double targetWeight, Double dischargeWeight,
    String outcome, String treatmentProtocol) {

        DischargeSummary d = dischargeRepository.findById(discharge_id)
            .orElseThrow(() -> new IllegalStateException(
                "Discharge summary with ID " + discharge_id + " does not exist"
            ));
        
        if (admissionDate != null && !admissionDate.equals(d.getAdmissionDate())) { d.setAdmissionDate(admissionDate); }
        if (dischargeDate != null && !dischargeDate.equals(d.getDischargeDate())) { d.setDischargeDate(dischargeDate); }
        if (admissionWeight != null && admissionWeight != d.getAdmissionWeight()) {  d.setAdmissionWeight(admissionWeight); }
        if (dischargeWeight != null && dischargeWeight != d.getDischargeWeight()) {  d.setDischargeWeight(dischargeWeight); }
        if (targetWeight != null && targetWeight != d.getTargetWeight()) {  d.setTargetWeight(targetWeight); }
        // if (contactNo != null && contactNo.length() > 0 && !contactNo.equals(d.getContactNo())) { d.setContactNo(contactNo); }       
        if (outcome != null && outcome.length() > 0 && !outcome.equals(d.getOutcome())) { d.setOutcome(outcome); }       
        if (treatmentProtocol != null && treatmentProtocol.length() > 0 && !treatmentProtocol.equals(d.getTreatmentProtocol())) { d.setTreatmentProtocol(treatmentProtocol); }       
    }

    public DischargeSummary addDischargeSummary(Long samId, DischargeSummary d) {
        System.out.println("SUMMARY IS" + d + " " + samId + " ");
        return this.patientRepository.findBySamId(samId).map(patient -> {   
            d.setPatient(patient);
            System.out.println("SUMMARY NOW IS " + d.getPatient().getName());
            return dischargeRepository.save(d);
        }).orElseThrow(() -> new ResourceNotFoundException("Patient with " + samId + " not found"));
    }
}
