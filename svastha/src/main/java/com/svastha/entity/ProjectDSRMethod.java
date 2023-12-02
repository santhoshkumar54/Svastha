package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectDSRMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String dsrMethod;

	private String soakingTime;

	private String seedRate;

	private String acres;

	private String nurseryArea;

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

	public String getDsrMethod() {
		return dsrMethod;
	}

	public void setDsrMethod(String dsrMethod) {
		this.dsrMethod = dsrMethod;
	}

	public String getSoakingTime() {
		return soakingTime;
	}

	public void setSoakingTime(String soakingTime) {
		this.soakingTime = soakingTime;
	}

	public String getAcres() {
		return acres;
	}

	public void setAcres(String acres) {
		this.acres = acres;
	}

	public String getNurseryArea() {
		return nurseryArea;
	}

	public void setNurseryArea(String nurseryArea) {
		this.nurseryArea = nurseryArea;
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