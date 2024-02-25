package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class viewFarmerCreatedweek {

	@Id
	private String name;

	private String farmcount;

	private String projectcount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFarmcount() {
		return farmcount;
	}

	public void setFarmcount(String farmcount) {
		this.farmcount = farmcount;
	}

	public String getProjectcount() {
		return projectcount;
	}

	public void setProjectcount(String projectcount) {
		this.projectcount = projectcount;
	}

}