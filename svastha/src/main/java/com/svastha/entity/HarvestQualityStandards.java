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
public class HarvestQualityStandards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String purchaseDate;

	private String lotNumber;

	private String purchaseCenter;

	private String quantity;

	private String packSize;

	private String labelled;

	private String moisture;

	@Column(name = "hr_percentage")
	private String hrPercentage;

	@Column(name = "broken_percentage")
	private String brokenPercentage;

	private String length;

	private String breadth;

	@Column(name = "dd_percentage")
	private String ddPercentage;

	@Column(name = "inert_matter_percentage")
	private String inertMatterPercentage;

	@Column(name = "admixture_percentage")
	private String admixturePercentage;

	@Column(name = "cheff_percentage")
	private String cheffPercentage;

	private String dispatchDate;

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

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getPurchaseCenter() {
		return purchaseCenter;
	}

	public void setPurchaseCenter(String purchaseCenter) {
		this.purchaseCenter = purchaseCenter;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	public String getLabelled() {
		return labelled;
	}

	public void setLabelled(String labelled) {
		this.labelled = labelled;
	}

	public String getMoisture() {
		return moisture;
	}

	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	public String getHrPercentage() {
		return hrPercentage;
	}

	public void setHrPercentage(String hrPercentage) {
		this.hrPercentage = hrPercentage;
	}

	public String getBrokenPercentage() {
		return brokenPercentage;
	}

	public void setBrokenPercentage(String brokenPercentage) {
		this.brokenPercentage = brokenPercentage;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getBreadth() {
		return breadth;
	}

	public void setBreadth(String breadth) {
		this.breadth = breadth;
	}

	public String getDdPercentage() {
		return ddPercentage;
	}

	public void setDdPercentage(String ddPercentage) {
		this.ddPercentage = ddPercentage;
	}

	public String getInertMatterPercentage() {
		return inertMatterPercentage;
	}

	public void setInertMatterPercentage(String inertMatterPercentage) {
		this.inertMatterPercentage = inertMatterPercentage;
	}

	public String getAdmixturePercentage() {
		return admixturePercentage;
	}

	public void setAdmixturePercentage(String admixturePercentage) {
		this.admixturePercentage = admixturePercentage;
	}

	public String getCheffPercentage() {
		return cheffPercentage;
	}

	public void setCheffPercentage(String cheffPercentage) {
		this.cheffPercentage = cheffPercentage;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
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
