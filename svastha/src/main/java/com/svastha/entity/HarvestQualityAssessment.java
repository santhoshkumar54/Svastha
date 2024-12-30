package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HarvestQualityAssessment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String moisturePercentage;

	private String methodOfMoistureChecking;

	private String greenGrainsPercentage;

	private String chaffyGrainsPercentage;

	private String impurities;

	private String otherQualityParameters;

	private String remarks;

	// Project association
	@ManyToOne
	private FarmProjects projects;

	// Audit fields
	@ManyToOne
	@JoinColumn(name = "created_by_pk1", updatable = false)
	private Users createdBy;

	@Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	// Getters and Setters
	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getMoisturePercentage() {
		return moisturePercentage;
	}

	public void setMoisturePercentage(String moisturePercentage) {
		this.moisturePercentage = moisturePercentage;
	}

	public String getMethodOfMoistureChecking() {
		return methodOfMoistureChecking;
	}

	public void setMethodOfMoistureChecking(String methodOfMoistureChecking) {
		this.methodOfMoistureChecking = methodOfMoistureChecking;
	}

	public String getGreenGrainsPercentage() {
		return greenGrainsPercentage;
	}

	public void setGreenGrainsPercentage(String greenGrainsPercentage) {
		this.greenGrainsPercentage = greenGrainsPercentage;
	}

	public String getChaffyGrainsPercentage() {
		return chaffyGrainsPercentage;
	}

	public void setChaffyGrainsPercentage(String chaffyGrainsPercentage) {
		this.chaffyGrainsPercentage = chaffyGrainsPercentage;
	}

	public String getImpurities() {
		return impurities;
	}

	public void setImpurities(String impurities) {
		this.impurities = impurities;
	}

	public String getOtherQualityParameters() {
		return otherQualityParameters;
	}

	public void setOtherQualityParameters(String otherQualityParameters) {
		this.otherQualityParameters = otherQualityParameters;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
}
