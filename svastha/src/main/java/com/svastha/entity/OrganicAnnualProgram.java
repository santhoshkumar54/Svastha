package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrganicAnnualProgram {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	@ManyToOne
	private MasterAnnualProgram annualProgram;

	@ManyToOne
	private MasterCrop crop;

	private String area;

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

	public MasterAnnualProgram getAnnualProgram() {
		return annualProgram;
	}

	public void setAnnualProgram(MasterAnnualProgram annualProgram) {
		this.annualProgram = annualProgram;
	}

	public MasterCrop getCrop() {
		return crop;
	}

	public void setCrop(MasterCrop crop) {
		this.crop = crop;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public FarmPlots getPlots() {
		return plots;
	}

	public void setPlots(FarmPlots plots) {
		this.plots = plots;
	}
}