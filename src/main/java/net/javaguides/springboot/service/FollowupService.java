package net.javaguides.springboot.service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Followup;
import net.javaguides.springboot.model.Patient;
import net.javaguides.springboot.model.AnganwadiWorker;
import net.javaguides.springboot.repository.FollowupRepository;
import net.javaguides.springboot.repository.PatientRepository;
import net.javaguides.springboot.repository.AnganwadiWorkerRepository;

@Service
public class FollowupService {

    private final FollowupRepository followupRepository;
    private final PatientRepository patientRepository;
    private final AnganwadiWorkerRepository anganwadiWorkerRepository;

    @Autowired
    public FollowupService(FollowupRepository followupRepository, PatientRepository patientRepository, AnganwadiWorkerRepository anganwadiWorkerRepository) {
        this.followupRepository = followupRepository;
        this.patientRepository = patientRepository;
        this.anganwadiWorkerRepository = anganwadiWorkerRepository;
    }
    public List<Followup> getFollowups() {
        return this.followupRepository.findAll();
    }

    public List<Followup> getFollowupByWorkerId(Long awwId){
        AnganwadiWorker w = this.anganwadiWorkerRepository.findByAwwId(awwId).orElseThrow(
            () -> new ResourceNotFoundException("No worker with given ID")
        );
        return w.getFollowups();
    }

    public Object getHealthRecord(Long followupId){
        return this.followupRepository.findHealthRecordById(followupId);
    }

    public Followup getFollowupById(Long id) {
        Followup d = this.followupRepository.findByFollowupId(id).orElseThrow(
            () -> new ResourceNotFoundException("No follow up with given ID")
        );
        System.out.println("IN SERVICE " + d.getLocation());
        return d;
    }

    public List<Followup> getFollowupBySamId(Long samId) {
        // return this.followupRepository.findBySamId(samId);
        Patient p = this.patientRepository.findBySamId(samId).orElseThrow(
            () -> new ResourceNotFoundException("No patient with given ID")
        );
        return p.getFollowups();
    }


    @Transactional
    public void updateFollowup(Long followupId, Long awwId, String location,
	LocalDate deadline_date, LocalDate completed_date, Boolean completed, Double height, Double weight, Double muac, String growthStatus, LocalDate created_date) {

        Followup d = followupRepository.findByFollowupId(followupId)
            .orElseThrow(() -> new IllegalStateException(
                "Follow up with ID " + followupId + " does not exist"
            ));
        
        // if (awwId != null && !awwId.equals(d.getWorkerId())) { d.setWorkerId(awwId); }
        if (location != null && !location.equals(d.getLocation())) { 
            d.setLocation(location); 
            List<AnganwadiWorker> workers = this.anganwadiWorkerRepository.findByKeyword(location, "");
            d.setWorker(workers.get(0));
        }
        if (deadline_date != null && !deadline_date.equals(d.getDeadline())) { d.setDeadline(deadline_date); }
        if (completed_date != null && !completed_date.equals(d.getCompletedOn())) { d.setCompletedOn(completed_date); }       
        if (completed != null && !completed.equals(d.getCompleted())) { d.setCompleted(completed); }   
        if (height != null && !height.equals(d.getHeight())) { d.setHeight(height); }
        if (weight != null && !weight.equals(d.getWeight())) { d.setWeight(weight); }       
        if (growthStatus != null && !growthStatus.equals(d.getGrowthStatus())) { d.setGrowthStatus(growthStatus); }       
        if (muac != null && !muac.equals(d.getMuac())) { d.setMuac(muac); }       
    }

    public Followup addFollowup(Long samId, Followup followup) {
        System.out.println("FOLLOWUP IS" + followup + " " + samId + " ");
        followup.setCreatedDate(LocalDateTime.now());
        List<AnganwadiWorker> workers = this.anganwadiWorkerRepository.findByKeyword(followup.getLocation(), "");
        return this.patientRepository.findBySamId(samId).map(patient -> {   
            followup.setPatient(patient);
            followup.setWorker(workers.get(0));
            System.out.println("FOLLOWUP NOW IS " + followup.getWorker().getWorkerId() + " " + followup.getWorker().getName());
            return followupRepository.save(followup);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + samId + " not found"));
    }
}
