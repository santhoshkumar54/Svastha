package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectWeedManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String brand;

	private String weedingDate;

	private String acres;

	private String emergence;

	@ManyToOne
	private FarmPlots plot;

	@ManyToOne
	private MasterWeedicide weedicide;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	@ManyToOne
	private FarmProjects projects;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAcres() {
		return acres;
	}

	public void setAcres(String acres) {
		this.acres = acres;
	}

	public String getEmergence() {
		return emergence;
	}

	public void setEmergence(String emergence) {
		this.emergence = emergence;
	}

	public String getWeedingDate() {
		return weedingDate;
	}

	public void setWeedingDate(String weedingDate) {
		this.weedingDate = weedingDate;
	}

	public FarmPlots getPlot() {
		return plot;
	}

	public void setPlot(FarmPlots plot) {
		this.plot = plot;
	}

	public MasterWeedicide getWeedicide() {
		return weedicide;
	}

	public void setWeedicide(MasterWeedicide weedicide) {
		this.weedicide = weedicide;
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