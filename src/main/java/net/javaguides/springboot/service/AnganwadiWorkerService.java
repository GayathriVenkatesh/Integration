package net.javaguides.springboot.service;
import java.time.LocalDate;
import java.util.List;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.AnganwadiWorker;
import net.javaguides.springboot.repository.AnganwadiWorkerRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnganwadiWorkerService {

    private final AnganwadiWorkerRepository anganwadiRepository;

    @Autowired
    public AnganwadiWorkerService(AnganwadiWorkerRepository anganwadiRepository) {
        this.anganwadiRepository = anganwadiRepository;
    }
    public List<AnganwadiWorker> getAnganwadiWorkers() {
        return this.anganwadiRepository.findAll();
    }

    public List<AnganwadiWorker> getByKeyword(String address, String locality){
        return this.anganwadiRepository.findByKeyword(address, locality);
    }

    public AnganwadiWorker getAnganwadiWorkerById(Long awwId) {
        AnganwadiWorker d = this.anganwadiRepository.findByAwwId(awwId).orElseThrow(
            () -> new ResourceNotFoundException("No worker with given ID")
        );
        return d;
    }

    @Transactional
    public void updateAnganwadiWorker(Long worker_id, String name, String contactNo, 
    String address, String locality) {

        AnganwadiWorker d = anganwadiRepository.findById(worker_id)
            .orElseThrow(() -> new IllegalStateException(
                "Discharge summary with ID " + worker_id + " does not exist"
            ));
        
        if (name != null && name.length() > 0 && !name.equals(d.getName())) { d.setName(name); }
        if (address != null && !address.equals(d.getAddress())) { d.setAddress(address); }
        if (locality != null && !locality.equals(d.getLocality())) { d.setLocality(locality); }
        if (contactNo != null && contactNo.length() > 0 && !contactNo.equals(d.getContactNo())) { d.setContactNo(contactNo); }       
    }

    public void addAnganwadiWorker(AnganwadiWorker d) {
        this.anganwadiRepository.save(d);
    }
}
