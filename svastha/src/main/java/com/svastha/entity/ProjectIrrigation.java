package com.svastha.entity;

import java.sql.Timestamp;

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

	@ManyToOne
	private MasterCropStage cropStage;

	private String irrigationDate;

	@ManyToOne
	private WaterSource irrigationSource;

	private String techniques;

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public MasterCropStage getCropStage() {
		return cropStage;
	}

	public void setCropStage(MasterCropStage cropStage) {
		this.cropStage = cropStage;
	}

	public String getIrrigationDate() {
		return irrigationDate;
	}

	public void setIrrigationDate(String irrigationDate) {
		this.irrigationDate = irrigationDate;
	}

	public WaterSource getIrrigationSource() {
		return irrigationSource;
	}

	public void setIrrigationSource(WaterSource irrigationSource) {
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