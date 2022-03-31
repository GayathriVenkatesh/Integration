// @Column(name = "first_name")
// private String firstName;

package net.javaguides.springboot.model;

// package com.example.demo.patient;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
// import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.bytebuddy.asm.Advice.Local;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {
	@SequenceGenerator(
		name = "patient_sequence",
		sequenceName = "patient_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "patient_sequence"
	)
	@Id
	private Long samId;
	private Long uhId, rchId;
	private String name;
	private LocalDate dob;
	// @Transient  
	private Integer age;
	private Character gender;
	private Boolean BPL;
	private String address, religion, caste, relationshipStatus, symptoms, referredBy;
	private String contactNumber;
	private LocalDate lastUpdated;
	// private HashMap<String, Double> health_params = new HashMap<String, Double>();

	@JsonManagedReference
    @OneToMany(mappedBy = "patient")
    private List<Followup> followups = new ArrayList<Followup>();

	@OneToMany(mappedBy = "patient")
    private List<DischargeSummary> discharge_summaries = new ArrayList<DischargeSummary>();

	@OneToMany(mappedBy = "patient")
    private List<HealthStatus> health_records = new ArrayList<HealthStatus>();

	public Patient() {}
	
	public Patient(Long UHID, Long rchId, String name, LocalDate dob, String contactNumber,
	Character gender, Boolean BPL, String addr, String religion, String caste, String relationshipStatus, 
	String symptoms, String refer, Float height, Float weight, Float muac, String growthStatus, LocalDate admission, 
	String other_symptoms) {
		this.uhId = UHID;
		// this.samId = samId;
		this.rchId = rchId;
		this.BPL = BPL;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.contactNumber = contactNumber;

		this.address = addr;
		this.relationshipStatus = relationshipStatus;
		this.caste = caste;
		this.symptoms = symptoms;
		this.referredBy = refer;
		this.religion = religion;

		this.health_records.add(new HealthStatus(admission, height.doubleValue(), weight.doubleValue(), muac.doubleValue(), growthStatus, other_symptoms));
	}

	public Patient(Long UHID, Long rchId, String name, LocalDate dob, String contactNumber,
	Character gender, Boolean BPL, String addr, String religion, String symptoms, List<Double> health) {
		this.uhId = UHID;
		// this.samId = samId;
		this.rchId = rchId;
		this.BPL = BPL;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.address = addr;
		this.symptoms = symptoms;
		this.religion = religion;
	}

	public Long getUhid() { return this.uhId; }
	public void setUhid(Long id) { this.uhId = id; }
	public Long getSamId() { return this.samId; }
	public void setSamId(Long id) { this.samId = id; }
	public Long getRch_id() { return this.rchId; }
	public void setRch_id(Long id) { this.rchId = id; }

	public Character getGender() { return this.gender; }
	public void setGender(Character g) { this.gender = g; }
	public String getContact_no() { return this.contactNumber; }
	public void setContact_no(String no) { this.contactNumber = no; }
	public Boolean getBpl() { return this.BPL; }
	public void setBpl(Boolean BPL) { this.BPL = BPL; }

	@Override
	public String toString() {
		return "Patient:" + "\nName: " + name + " " + health_records + 
		"\nDOB:" + dob + "\nAge: " + age + 
		"\nAddress: " + address + "\nRelation: " + relationshipStatus + ", referred by=" + referredBy
		+ ", symptoms=" + symptoms + ", religion=" + religion + ", caste=" + caste + '}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getReferred_by() {
		return referredBy;
	}

	public void setReferred_by(String referredBy) {
		this.referredBy = referredBy;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getRelationship() {
		return relationshipStatus;
	}

	public void setRelationship(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public List<Followup> getFollowups() {
		return followups;
	}

	public void setFollowups(List<Followup> followups) {
		this.followups = followups;
	}

	public List<DischargeSummary> getDischarge_summaries() {
		return this.discharge_summaries;
	}

	public void setDischarge_summaries(List<DischargeSummary> discharge_summaries) {
		this.discharge_summaries = discharge_summaries;
	}

	public List<HealthStatus> getHealth_records() {
		return health_records;
	}

	public void setHealth_records(List<HealthStatus> health_records) {
		this.health_records = health_records;
	}
}

