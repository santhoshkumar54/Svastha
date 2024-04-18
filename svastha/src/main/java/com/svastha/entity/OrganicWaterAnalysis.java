package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrganicWaterAnalysis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String alkalinity;

	private String ph;

	private String bicarbonates;

	private String dissolvedCalcium;

	private String carbonates;

	private String riversideClassification;

	private String chloride;

	private String conductivity;

	private String hardness;

	private String phosphate;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	@ManyToOne
	private FarmProjects projects;

	@ManyToOne
	private FarmPlots plots;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public FarmProjects getProjects() {
		return projects;
	}

	public void setProjects(FarmProjects projects) {
		this.projects = projects;
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

	public FarmPlots getPlots() {
		return plots;
	}

	public void setPlots(FarmPlots plots) {
		this.plots = plots;
	}

	public String getAlkalinity() {
		return alkalinity;
	}

	public void setAlkalinity(String alkalinity) {
		this.alkalinity = alkalinity;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getBicarbonates() {
		return bicarbonates;
	}

	public void setBicarbonates(String bicarbonates) {
		this.bicarbonates = bicarbonates;
	}

	public String getDissolvedCalcium() {
		return dissolvedCalcium;
	}

	public void setDissolvedCalcium(String dissolvedCalcium) {
		this.dissolvedCalcium = dissolvedCalcium;
	}

	public String getCarbonates() {
		return carbonates;
	}

	public void setCarbonates(String carbonates) {
		this.carbonates = carbonates;
	}

	public String getRiversideClassification() {
		return riversideClassification;
	}

	public void setRiversideClassification(String riversideClassification) {
		this.riversideClassification = riversideClassification;
	}

	public String getChloride() {
		return chloride;
	}

	public void setChloride(String chloride) {
		this.chloride = chloride;
	}

	public String getConductivity() {
		return conductivity;
	}

	public void setConductivity(String conductivity) {
		this.conductivity = conductivity;
	}

	public String getHardness() {
		return hardness;
	}

	public void setHardness(String hardness) {
		this.hardness = hardness;
	}

	public String getPhosphate() {
		return phosphate;
	}

	public void setPhosphate(String phosphate) {
		this.phosphate = phosphate;
	}

}