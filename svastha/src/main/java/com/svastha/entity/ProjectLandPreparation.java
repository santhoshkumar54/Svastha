package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProjectLandPreparation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;
	
	@ManyToOne
	private MasterCropVariety variety;

	private String dateOfPreparation;

	private String acresPrepared;

	private String method;

	private String fieldPreparation;
	
	private String noOfTimes;

	@ManyToOne
	private FarmProjects project;

	@ManyToOne
	@JoinColumn(name = "created_by_pk1", updatable = false)
	private Users createdBy;

	@Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

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

	public String getDateOfPreparation() {
		return dateOfPreparation;
	}

	public void setDateOfPreparation(String dateOfPreparation) {
		this.dateOfPreparation = dateOfPreparation;
	}

	public String getAcresPrepared() {
		return acresPrepared;
	}

	public void setAcresPrepared(String acresPrepared) {
		this.acresPrepared = acresPrepared;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public MasterCropVariety getVariety() {
		return variety;
	}

	public void setVariety(MasterCropVariety variety) {
		this.variety = variety;
	}

	public String getFieldPreparation() {
		return fieldPreparation;
	}

	public void setFieldPreparation(String fieldPreparation) {
		this.fieldPreparation = fieldPreparation;
	}

	public String getNoOfTimes() {
		return noOfTimes;
	}

	public void setNoOfTimes(String noOfTimes) {
		this.noOfTimes = noOfTimes;
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