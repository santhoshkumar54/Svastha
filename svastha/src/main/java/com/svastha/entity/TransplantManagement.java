package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TransplantManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String variety;

	private String dateOfTransplanting;

	private String dateOfDirectSowing;

	private String manualCoverage;

	private String mechanicalCoverage;

	private String transplanterCoverage;

	private String directCoverage;

	@ManyToOne
	private FarmProjects project;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public String getDateOfTransplanting() {
		return dateOfTransplanting;
	}

	public void setDateOfTransplanting(String dateOfTransplanting) {
		this.dateOfTransplanting = dateOfTransplanting;
	}

	public String getDateOfDirectSowing() {
		return dateOfDirectSowing;
	}

	public void setDateOfDirectSowing(String dateOfDirectSowing) {
		this.dateOfDirectSowing = dateOfDirectSowing;
	}

	public String getManualCoverage() {
		return manualCoverage;
	}

	public void setManualCoverage(String manualCoverage) {
		this.manualCoverage = manualCoverage;
	}

	public String getMechanicalCoverage() {
		return mechanicalCoverage;
	}

	public void setMechanicalCoverage(String mechanicalCoverage) {
		this.mechanicalCoverage = mechanicalCoverage;
	}

	public String getTransplanterCoverage() {
		return transplanterCoverage;
	}

	public void setTransplanterCoverage(String transplanterCoverage) {
		this.transplanterCoverage = transplanterCoverage;
	}

	public String getDirectCoverage() {
		return directCoverage;
	}

	public void setDirectCoverage(String directCoverage) {
		this.directCoverage = directCoverage;
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