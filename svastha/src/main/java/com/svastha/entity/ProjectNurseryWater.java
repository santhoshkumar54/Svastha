package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectNurseryWater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String seedlingAge;

	private String irrigationDate;

	@ManyToOne
	private FarmPlots plot;

	@ManyToOne
	private MasterCropVariety variety;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;
	
	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	@ManyToOne
	private FarmProjects projects;

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


	public String getSeedlingAge() {
		return seedlingAge;
	}

	public void setSeedlingAge(String seedlingAge) {
		this.seedlingAge = seedlingAge;
	}

	public String getIrrigationDate() {
		return irrigationDate;
	}

	public void setIrrigationDate(String irrigationDate) {
		this.irrigationDate = irrigationDate;
	}

	public FarmPlots getPlot() {
		return plot;
	}

	public void setPlot(FarmPlots plot) {
		this.plot = plot;
	}

	public MasterCropVariety getVariety() {
		return variety;
	}

	public void setVariety(MasterCropVariety variety) {
		this.variety = variety;
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