package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrganicHarvestEstimates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String estimatedDate;

	private String estimatedYield;

	@ManyToOne
	private FarmProjects project;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getEstimatedDate() {
		return estimatedDate;
	}

	public void setEstimatedDate(String estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public String getEstimatedYield() {
		return estimatedYield;
	}

	public void setEstimatedYield(String estimatedYield) {
		this.estimatedYield = estimatedYield;
	}

	public FarmProjects getProject() {
		return project;
	}

	public void setProject(FarmProjects project) {
		this.project = project;
	}
}
