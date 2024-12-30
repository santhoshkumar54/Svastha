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
public class HarvestPackagingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String packagingMaterial;

	private String numberOfBagsRequired;

	private String packingSize;

	private String costOfBags;

	private String ropeCost;

	private String stitchingCost;

	private String numberOfLoadMansInvolved;

	// Project association
	@ManyToOne
	private FarmProjects projects;

	// Audit fields
	@ManyToOne
	@JoinColumn(name = "created_by_pk1", updatable = false)
	private Users createdBy;

	@Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	// Getters and Setters
	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getPackagingMaterial() {
		return packagingMaterial;
	}

	public void setPackagingMaterial(String packagingMaterial) {
		this.packagingMaterial = packagingMaterial;
	}

	public String getNumberOfBagsRequired() {
		return numberOfBagsRequired;
	}

	public void setNumberOfBagsRequired(String numberOfBagsRequired) {
		this.numberOfBagsRequired = numberOfBagsRequired;
	}

	public String getPackingSize() {
		return packingSize;
	}

	public void setPackingSize(String packingSize) {
		this.packingSize = packingSize;
	}

	public String getCostOfBags() {
		return costOfBags;
	}

	public void setCostOfBags(String costOfBags) {
		this.costOfBags = costOfBags;
	}

	public String getRopeCost() {
		return ropeCost;
	}

	public void setRopeCost(String ropeCost) {
		this.ropeCost = ropeCost;
	}

	public String getStitchingCost() {
		return stitchingCost;
	}

	public void setStitchingCost(String stitchingCost) {
		this.stitchingCost = stitchingCost;
	}

	public String getNumberOfLoadMansInvolved() {
		return numberOfLoadMansInvolved;
	}

	public void setNumberOfLoadMansInvolved(String numberOfLoadMansInvolved) {
		this.numberOfLoadMansInvolved = numberOfLoadMansInvolved;
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
