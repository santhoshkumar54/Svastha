package com.svastha.entity;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectSowingPlots {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;
	
	@ManyToOne
	private ProjectSowingData sowing;
	
	@ManyToOne FarmPlots plots;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public ProjectSowingData getSowing() {
		return sowing;
	}

	public void setSowing(ProjectSowingData sowing) {
		this.sowing = sowing;
	}

	public FarmPlots getPlots() {
		return plots;
	}

	public void setPlots(FarmPlots plots) {
		this.plots = plots;
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
}