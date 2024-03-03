package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class viewHeadlines {

	@Id
	private String farmers;

	private String projects;

	private String yield;

	private String acres;

	public String getFarmers() {
		return farmers;
	}

	public void setFarmers(String farmers) {
		this.farmers = farmers;
	}

	public String getProjects() {
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public String getAcres() {
		return acres;
	}

	public void setAcres(String acres) {
		this.acres = acres;
	}

}