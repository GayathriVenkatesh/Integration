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
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.DischargeSummary;
import net.javaguides.springboot.service.DischargeSummaryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class DischargeSummaryController {

    private final DischargeSummaryService dischargeService;

    @Autowired
    public DischargeSummaryController(DischargeSummaryService dischargeService) {
        this.dischargeService = dischargeService;
    }

    @GetMapping("/view-discharge-summaries")
    public List<DischargeSummary> getDischargeSummaries() { 
        System.out.print("INSIDE THIS FUNC"); 
        return dischargeService.getDischargeSummaries();
    }

    @GetMapping("/view-discharge-summary-patient/{samId}")
	public List<DischargeSummary> getDischargeSummaryBySamId(@PathVariable Long samId) {
		return dischargeService.getDischargeSummaryBySamId(samId);	
	}

    @GetMapping("/delete-discharge-summary/{discharge_id}")
    public List<DischargeSummary> deleteDischargeSummary(@PathVariable Long discharge_id) { 
        List<DischargeSummary> p = dischargeService.getDischargeSummaries();
        List<DischargeSummary> ans = new ArrayList<DischargeSummary>();
        for (DischargeSummary discharge: p) {
            if(discharge.getDischargeId() != discharge_id) {
                ans.add(discharge);
            }
        }
        return ans;
    }   

    // @RequestMapping(value = "/search-summary")
    // @ResponseBody
    // public List<DischargeSummary> search(@RequestParam("samId") String samId, 
    //                             @RequestParam("discharge_id") String discharge_id,
    //                             @RequestParam("name") String name
    //                             // @RequestParam("discharge_date") LocalDate discharge_date
    //                         ) {
    //     List<DischargeSummary> p = dischargeService.getByKeyword(discharge_id, name);    
    //     return p; 
    // }

    @GetMapping("/view-discharge-summary/{discharge_id}")
	public ResponseEntity<DischargeSummary> getDischargeSummaryById(@PathVariable Long discharge_id) {
		DischargeSummary d = dischargeService.getDischargeSummaryById(discharge_id);	
        return ResponseEntity.ok(d);			
	}

    @PostMapping("/discharge-summary/{samId}")
	public void addDischargeSummary(@PathVariable Long samId, @RequestBody DischargeSummary d) {
		dischargeService.addDischargeSummary(samId, d);
	}

    @PutMapping("/edit-discharge-summary/{samId}/{discharge_id}")
	public ResponseEntity<DischargeSummary> updateDischargeSummary(@PathVariable Long samId, @PathVariable Long discharge_id, @RequestBody DischargeSummary d) {
        DischargeSummary discharge = dischargeService.getDischargeSummaryById(discharge_id);
        // discharge.setName(d.getName());
        discharge.setAdmissionDate(d.getAdmissionDate());
        discharge.setDischargeDate(d.getDischargeDate());
        discharge.setAdmissionWeight(d.getAdmissionWeight());
        discharge.setDischargeWeight(d.getDischargeWeight());
        discharge.setTargetWeight(d.getTargetWeight());
        // discharge.setContactNo(d.getContactNo());
        discharge.setTreatmentProtocol(d.getTreatmentProtocol());
        discharge.setOutcome(d.getOutcome());

        dischargeService.addDischargeSummary(samId, discharge);
        return ResponseEntity.ok(discharge);
    }
}
