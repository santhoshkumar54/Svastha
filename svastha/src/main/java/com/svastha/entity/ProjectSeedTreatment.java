package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectSeedTreatment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String seedRate;

	private String saltSolution;

	private String seedTreatment;

	private String seedSource;

	private String brand;

	private String company;

	private String seedClass;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	@ManyToOne
	private FarmProjects projects;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getSeedRate() {
		return seedRate;
	}

	public void setSeedRate(String seedRate) {
		this.seedRate = seedRate;
	}

	public String getSaltSolution() {
		return saltSolution;
	}

	public void setSaltSolution(String saltSolution) {
		this.saltSolution = saltSolution;
	}

	public String getSeedTreatment() {
		return seedTreatment;
	}

	public void setSeedTreatment(String seedTreatment) {
		this.seedTreatment = seedTreatment;
	}

	public String getSeedSource() {
		return seedSource;
	}

	public void setSeedSource(String seedSource) {
		this.seedSource = seedSource;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSeedClass() {
		return seedClass;
	}

	public void setSeedClass(String seedClass) {
		this.seedClass = seedClass;
	}

	public Users getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Users createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public FarmProjects getProjects() {
		return projects;
	}

	public void setProjects(FarmProjects projects) {
		this.projects = projects;
	}
}