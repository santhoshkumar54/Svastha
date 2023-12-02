package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectIrrigation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private int number;

	private String cropStage;

	private String irrigationDate;

	private String irrigationSource;

	private String techniques;

	@ManyToOne
	private FarmProjects projects;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCropStage() {
		return cropStage;
	}

	public void setCropStage(String cropStage) {
		this.cropStage = cropStage;
	}

	public String getIrrigationDate() {
		return irrigationDate;
	}

	public void setIrrigationDate(String irrigationDate) {
		this.irrigationDate = irrigationDate;
	}

	public String getIrrigationSource() {
		return irrigationSource;
	}

	public void setIrrigationSource(String irrigationSource) {
		this.irrigationSource = irrigationSource;
	}

	public String getTechniques() {
		return techniques;
	}

	public void setTechniques(String techniques) {
		this.techniques = techniques;
	}

	public FarmProjects getProjects() {
		return projects;
	}

	public void setProjects(FarmProjects projects) {
		this.projects = projects;
	}
}