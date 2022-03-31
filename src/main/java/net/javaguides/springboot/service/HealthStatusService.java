package net.javaguides.springboot.service;
import java.time.LocalDate;
import java.util.List;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.HealthStatus;
import net.javaguides.springboot.model.Patient;
import net.javaguides.springboot.repository.HealthStatusRepository;
import net.javaguides.springboot.repository.PatientRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthStatusService {

    private final HealthStatusRepository healthStatusRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public HealthStatusService(HealthStatusRepository healthStatusRepository, PatientRepository patientRepository) {
        this.healthStatusRepository = healthStatusRepository;
        this.patientRepository = patientRepository;
    }
    public List<HealthStatus> getHealthStatuses() {
        return this.healthStatusRepository.findAll();
    }

    public List<HealthStatus> getByKeyword(String hsId){
        return this.healthStatusRepository.findByKeyword(hsId);
    }

    public HealthStatus getHealthStatusById(Long id) {
        HealthStatus d = this.healthStatusRepository.findByHsId(id).orElseThrow(
            () -> new ResourceNotFoundException("No health status with given ID")
        );
        return d;
    }

    public List<HealthStatus> getHealthStatusBySamId(Long samId) {
        Patient p = this.patientRepository.findBySamId(samId).orElseThrow(
            () -> new ResourceNotFoundException("No patient with given ID")
        );
        System.out.println("PATIENT IS: " + p);
        return p.getHealth_records();
    }

    @Transactional
    public void updateHealthStatus(Long hsId, Float height, Float weight, Float muac, String growthStatus, String otherSymptoms) {

        HealthStatus d = healthStatusRepository.findByHsId(hsId)
            .orElseThrow(() -> new IllegalStateException(
                "Follow up with ID " + hsId + " does not exist"
            ));
        
        if (height != null && !height.equals(d.getHeight())) { d.setHeight(height.doubleValue()); }
        if (weight != null && !weight.equals(d.getWeight())) { d.setWeight(weight.doubleValue()); }
        if (muac != null && !muac.equals(d.getMuac())) { d.setMuac(muac.doubleValue()); }
        if (growthStatus != null && !growthStatus.equals(d.getGrowthStatus())) { d.setGrowthStatus(growthStatus); }       
        if (otherSymptoms != null && !otherSymptoms.equals(d.getOtherSymptoms())) { d.setOtherSymptoms(otherSymptoms); }       
    }

    public HealthStatus addHealthStatus(Long samId, HealthStatus d) {
        return this.patientRepository.findBySamId(samId).map(patient -> {   
            d.setPatient(patient);
            return healthStatusRepository.save(d);
        }).orElseThrow(() -> new ResourceNotFoundException("Patient " + samId + " not found"));
    }
}
