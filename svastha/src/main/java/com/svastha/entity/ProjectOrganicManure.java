package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectOrganicManure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	@ManyToOne
	private MasterCropStage stage;

	private String usedDate;

	private String dose;

	private String method;

	@ManyToOne
	private FarmPlots plot;

	@ManyToOne
	private MasterOrganicManure manure;

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

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public FarmPlots getPlot() {
		return plot;
	}

	public void setPlot(FarmPlots plot) {
		this.plot = plot;
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

	public MasterCropStage getStage() {
		return stage;
	}

	public void setStage(MasterCropStage stage) {
		this.stage = stage;
	}

	public String getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(String usedDate) {
		this.usedDate = usedDate;
	}

	public MasterOrganicManure getManure() {
		return manure;
	}

	public void setManure(MasterOrganicManure manure) {
		this.manure = manure;
	}

}