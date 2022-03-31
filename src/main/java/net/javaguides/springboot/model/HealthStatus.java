package net.javaguides.springboot.model;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "health_status")
public class HealthStatus implements Serializable {
	@SequenceGenerator(
		name = "healthstatus_sequence",
		sequenceName = "healthstatus_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "healthstatus_sequence"
	)
	@Id
	private Long hsId;
	private Double height;
	private Double weight;
    private Double muac;
	private String growthStatus;
    private String otherSymptoms; 
	private LocalDate date;
	// private Boolean discharged

	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "sam_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;
	
	public Long getHsId() {
		return hsId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setHsId(Long hsId) {
		this.hsId = hsId;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getMuac() {
		return muac;
	}
	public void setMuac(Double muac) {
		this.muac = muac;
	}
	public String getGrowthStatus() {
		return growthStatus;
	}
	public void setGrowthStatus(String growthStatus) {
		this.growthStatus = growthStatus;
	}
    public String getOtherSymptoms() {
		return otherSymptoms;
	}
	public void setOtherSymptoms(String otherSymptoms) {
		this.otherSymptoms = otherSymptoms;
	}
	public HealthStatus(LocalDate date, Double height, Double weight, Double muac, String growthStatus, String otherSymptoms) {
		this.date = date;
		this.height = height;
        this.weight = weight;
        this.muac = muac;
        this.growthStatus = growthStatus;
        this.otherSymptoms = otherSymptoms;
	}
	public HealthStatus() {
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	@Override
	public String toString() {
		return height + " " + weight + " " + muac + " " + growthStatus;
	}
}

