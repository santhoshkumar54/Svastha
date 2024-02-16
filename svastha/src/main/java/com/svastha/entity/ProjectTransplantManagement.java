package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectTransplantManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	@ManyToOne
	private MasterCropVariety variety;

	private String dateOfTransplanting;

	private String duration;

	private String acres;

	private String seedlingAge;

	private String spacing;

	private String hill;

	@ManyToOne
	private FarmPlots plots;
	
	@ManyToOne
	private FarmProjects project;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	private String cost;

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public MasterCropVariety getVariety() {
		return variety;
	}

	public void setVariety(MasterCropVariety variety) {
		this.variety = variety;
	}

	public String getDateOfTransplanting() {
		return dateOfTransplanting;
	}

	public void setDateOfTransplanting(String dateOfTransplanting) {
		this.dateOfTransplanting = dateOfTransplanting;
	}
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getAcres() {
		return acres;
	}

	public void setAcres(String acres) {
		this.acres = acres;
	}

	public String getSeedlingAge() {
		return seedlingAge;
	}

	public void setSeedlingAge(String seedlingAge) {
		this.seedlingAge = seedlingAge;
	}

	public String getSpacing() {
		return spacing;
	}

	public void setSpacing(String spacing) {
		this.spacing = spacing;
	}

	public String getHill() {
		return hill;
	}

	public void setHill(String hill) {
		this.hill = hill;
	}

	public FarmPlots getPlots() {
		return plots;
	}

	public void setPlots(FarmPlots plots) {
		this.plots = plots;
	}

	public FarmProjects getProject() {
		return project;
	}

	public void setProject(FarmProjects project) {
		this.project = project;
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

	public Users getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Users updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Timestamp updatedDt) {
		this.updatedDt = updatedDt;
	}
}