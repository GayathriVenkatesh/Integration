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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "discharge_summary")
public class DischargeSummary implements Serializable {
	@SequenceGenerator(
		name = "discharge_summary_sequence",
		sequenceName = "discharge_summary_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "discharge_summary_sequence"
	)
	@Id
	private Long dischargeId;
	// private String name;
	private LocalDate admissionDate, dischargeDate;
	private Double admissionWeight, targetWeight, dischargeWeight;
	private String outcome, treatmentProtocol;
	private LocalDateTime createdDate;

	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "sam_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

	public DischargeSummary() {
	}
	
	public DischargeSummary(LocalDate admissionDate, LocalDate dischargeDate,
			Double admissionWeight, Double targetWeight, Double dischargeWeight, String outcome,
			String treatmentProtocol) {
		// this.dischargeId = dischargeId;
		// this.name = name;
		this.admissionDate = admissionDate;
		this.dischargeDate = dischargeDate;
		this.admissionWeight = admissionWeight;
		this.targetWeight = targetWeight;
		this.dischargeWeight = dischargeWeight;
		// this.contactNo = contactNo;
		this.outcome = outcome;
		this.treatmentProtocol = treatmentProtocol;
	}
	public Long getDischargeId() {
		return dischargeId;
	}
	public void setDischargeId(Long dischargeId) {
		this.dischargeId = dischargeId;
	}
	// public String getName() {
	// 	return name;
	// }
	// public void setName(String name) {
	// 	this.name = name;
	// }
	public LocalDate getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}
	public LocalDate getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public Double getAdmissionWeight() {
		return admissionWeight;
	}
	public void setAdmissionWeight(Double admissionWeight) {
		this.admissionWeight = admissionWeight;
	}
	public Double getTargetWeight() {
		return targetWeight;
	}
	public void setTargetWeight(Double targetWeight) {
		this.targetWeight = targetWeight;
	}
	public Double getDischargeWeight() {
		return dischargeWeight;
	}
	public void setDischargeWeight(Double dischargeWeight) {
		this.dischargeWeight = dischargeWeight;
	}
	// public String getContactNo() {
	// 	return contactNo;
	// }
	// public void setContactNo(String contactNo) {
	// 	this.contactNo = contactNo;
	// }
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getTreatmentProtocol() {
		return treatmentProtocol;
	}
	public void setTreatmentProtocol(String treatmentProtocol) {
		this.treatmentProtocol = treatmentProtocol;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}


}

