package net.javaguides.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.AnganwadiWorker;
import net.javaguides.springboot.service.AnganwadiWorkerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class AnganwadiWorkerController {

    private final AnganwadiWorkerService anganwadiService;

    @Autowired
    public AnganwadiWorkerController(AnganwadiWorkerService anganwadiService) {
        this.anganwadiService = anganwadiService;
    }

    @GetMapping("/view-anganwadi-workers")
    public List<AnganwadiWorker> getDischargeSummaries() { 
        return anganwadiService.getAnganwadiWorkers();
    }

    @GetMapping("/delete-anganwadi-worker/{worker_id}")
    public List<AnganwadiWorker> deleteAnganwadiWorker(@PathVariable Long worker_id) { 
        List<AnganwadiWorker> p = anganwadiService.getAnganwadiWorkers();
        List<AnganwadiWorker> ans = new ArrayList<AnganwadiWorker>();
        for (AnganwadiWorker worker: p) {
            if(worker.getWorkerId() != worker_id) {
                ans.add(worker);
            }
        }
        return ans;
    }   

    @RequestMapping(value = "/search-worker")
    @ResponseBody
    public List<AnganwadiWorker> search(@RequestParam("address") String address, 
                                @RequestParam("locality") String locality
                            ) {
        List<AnganwadiWorker> p = anganwadiService.getByKeyword(address, locality);    
        return p; 
    }

    @GetMapping("/view-anganwadi-worker/{worker_id}")
	public ResponseEntity<AnganwadiWorker> getAnganwadiWorkerById(@PathVariable Long worker_id) {
		AnganwadiWorker d = anganwadiService.getAnganwadiWorkerById(worker_id);	
        return ResponseEntity.ok(d);			
	}

    @PostMapping("/anganwadi-worker")
	public void addAnganwadiWorker(@RequestBody AnganwadiWorker d) {
		anganwadiService.addAnganwadiWorker(d);
	}

    @PutMapping("/edit-anganwadi-worker/{worker_id}")
	public ResponseEntity<AnganwadiWorker> updateAnganwadiWorker(@PathVariable Long worker_id, @RequestBody AnganwadiWorker d) {
        AnganwadiWorker worker = anganwadiService.getAnganwadiWorkerById(worker_id);
        worker.setName(d.getName());
        worker.setContactNo(d.getContactNo());
        worker.setAddress(d.getAddress());
        worker.setLocality(d.getLocality());

        anganwadiService.addAnganwadiWorker(worker);
        return ResponseEntity.ok(worker);
    }
}
