package net.javaguides.springboot.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import net.bytebuddy.asm.Advice.Local;

@Entity
@Table(name = "followup")
public class Followup implements Serializable {
	@SequenceGenerator(
		name = "followup_sequence",
		sequenceName = "followup_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "followup_sequence"
	)
	@Id
	private Long followupId;
	// private Long samId;
	// private Long awwId;
	private LocalDate deadlineDate, completedDate;
	private Boolean completed;
	private Double height, weight, muac;
	private String growthStatus, location;


	private LocalDateTime createdDate;
	
	@OneToOne(targetEntity = HealthStatus.class)
	@JoinColumn(name = "hs_id")
	private HealthStatus healthStatus;

	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "sam_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

	@JsonBackReference(value = "worker-followup")
	@ManyToOne
    @JoinColumn(name = "awwId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AnganwadiWorker worker;

	public Followup(Long followupId, LocalDate deadline_date, String location, LocalDate completed_date,
			Boolean completed, Double height, Double weight, Double muac, String growthStatus) {
		this.followupId = followupId;
		// this.samId = samId;
		// this.awwId = awwId;
		this.deadlineDate = deadline_date;
		this.location = location;
		this.completedDate = completed_date;
		this.completed = completed;
		this.healthStatus = new HealthStatus(LocalDate.now(), height, weight, muac, growthStatus, "");
		// this.height = height;
		// this.weight = weight;
		// this.muac = muac;
		// this.growthStatus = growthStatus;
	}

	public Long getFollowupId() {
		return followupId;
	}
	public void setFollowupId(Long followupId) {
		this.followupId = followupId;
	}

	// public Long getSamId() {
	// 	return this.samId;
	// }
	// public void setSamId(Long samId) {
	// 	this.samId = samId;
	// }
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	// public Long getWorkerId() {
	// 	return awwId;
	// }
	// public void setWorkerId(Long awwId) {
	// 	this.awwId = awwId;
	// }
	public LocalDate getDeadline() {
		return deadlineDate;
	}
	public void setDeadline(LocalDate deadline_date) {
		this.deadlineDate = deadline_date;
	}
	public LocalDate getCompletedOn() {
		return completedDate;
	}
	public void setCompletedOn(LocalDate completed_date) {
		this.completedDate = completed_date;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	public Followup(Long followupId, LocalDate deadline_date, String location, LocalDate completed_date,
			Boolean completed) {
		this.followupId = followupId;
		// this.samId = samId;
		// this.awwId = awwId;
		this.deadlineDate = deadline_date;
		this.completedDate = completed_date;
		this.completed = completed;
		this.location = location;
	}
	public Followup() {
	}
	public Double getHeight() {
		return this.healthStatus.getHeight();
	}
	public void setHeight(Double height) {
		this.healthStatus.setHeight(height); 
	}
	public Double getWeight() {
		return healthStatus.getWeight();
	}
	public void setWeight(Double weight) {
		// this.weight = weight;
	}
	public Double getMuac() {
		return healthStatus.getMuac();
	}

	public void setMuac(Double muac) {
		// this.muac = muac;
		this.healthStatus.setMuac(muac);
	}
	public String getGrowthStatus() {
		return healthStatus.getGrowthStatus();
	}
	public void setGrowthStatus(String growthStatus) {
		// this.growthStatus = growthStatus;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public AnganwadiWorker getWorker() {
		return worker;
	}

	public void setWorker(AnganwadiWorker worker) {
		this.worker = worker;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(LocalDate deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public LocalDate getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(LocalDate completedDate) {
		this.completedDate = completedDate;
	}

	public HealthStatus getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(HealthStatus healthStatus) {
		this.healthStatus = healthStatus;
	}
}

