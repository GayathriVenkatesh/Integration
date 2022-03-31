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

import net.javaguides.springboot.model.Followup;
import net.javaguides.springboot.service.FollowupService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class FollowupController {

    private final FollowupService followupService;

    @Autowired
    public FollowupController(FollowupService followupService) {
        this.followupService = followupService;
    }

    @GetMapping("/view-followups")
	public List<Followup> getFollowups() {
		return followupService.getFollowups();
	}

    @GetMapping("/view-followup/{followupId}")
	public ResponseEntity<Followup> getFollowupById(@PathVariable Long followupId) {
		Followup p = followupService.getFollowupById(followupId);	
        return ResponseEntity.ok(p);			
	}

    @GetMapping("/view-patient-followup/{samId}")
	public List<Followup> getFollowupBySamId(@PathVariable Long samId) {
		return followupService.getFollowupBySamId(samId);	
	}

    @GetMapping("/view-worker-followup/{awwId}")
	public List<Followup> getFollowupByWorkerId(@PathVariable Long awwId) {
		return followupService.getFollowupByWorkerId(awwId);	
	}

    @GetMapping("/view-health-record/{followupId}")
	public ResponseEntity<Object> getHealthRecord(@PathVariable Long followupId) {
		Object p = followupService.getHealthRecord(followupId);	
        return ResponseEntity.ok(p);			
	}

    @PostMapping("/followup/{samId}")
	public Followup addFollowup(@PathVariable Long samId, @RequestBody Followup p) {
		return followupService.addFollowup(samId, p);
	}
    
    @PutMapping("/edit-followup/{samId}/{followupId}")
	public ResponseEntity<Followup> updateFollowup(@PathVariable Long samId, @PathVariable Long followupId, @RequestBody Followup p) {
        Followup followup = followupService.getFollowupById(followupId);
        // followup.setWorkerId(p.getWorkerId());
        followup.setDeadline(p.getDeadline());
        followup.setCompletedOn(p.getCompletedOn());
        followup.setCompleted(p.getCompleted());
        followup.setLocation(p.getLocation());
        followup.setHeight(p.getHeight());
        followup.setWeight(p.getWeight());
        followup.setMuac(p.getMuac());
        followup.setGrowthStatus(p.getGrowthStatus());
        followupService.addFollowup(samId, followup);
        return ResponseEntity.ok(followup);
    }

    // @RequestMapping(value = "/search-followup")
    // @ResponseBody
    // public List<Followup> search(@RequestParam("awwId") String awwId,
    //                             @RequestParam("completed") String completed
    //                         ) {
    //     List<Followup> p = followupService.getByKeyword(awwId, completed);    
    //     return p; 
    // }

    @GetMapping("/delete-followup/{followup_id}")
    public List<Followup> deleteFollowup(@PathVariable Long followup_id) { 
        List<Followup> p = followupService.getFollowups();
        List<Followup> ans = new ArrayList<Followup>();
        for (Followup f: p) {
            if(f.getFollowupId() != followup_id) {
                ans.add(f);
            }
        }
        return ans;
    }  
}
