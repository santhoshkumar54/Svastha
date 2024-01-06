package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectPacking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	@ManyToOne
	private MasterCropVariety variety;

	private String harvestDate;

	private String harvestMethod;

	private String threshingDate;

	private String threshingMethod;

	private String cleaningDate;

	private String moisture;

	private String packingMaterial;

	private String packSize;

	private String numberOfBags;

	private String grossWeight;

	private String emptyWeight;

	private String nettWeight;

	private String labelled;

	@ManyToOne
	private FarmProjects projects;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public MasterCropVariety getVariety() {
		return variety;
	}

	public void setVariety(MasterCropVariety variety) {
		this.variety = variety;
	}

	public String getHarvestDate() {
		return harvestDate;
	}

	public void setHarvestDate(String harvestDate) {
		this.harvestDate = harvestDate;
	}

	public String getHarvestMethod() {
		return harvestMethod;
	}

	public void setHarvestMethod(String harvestMethod) {
		this.harvestMethod = harvestMethod;
	}

	public String getThreshingDate() {
		return threshingDate;
	}

	public void setThreshingDate(String threshingDate) {
		this.threshingDate = threshingDate;
	}

	public String getThreshingMethod() {
		return threshingMethod;
	}

	public void setThreshingMethod(String threshingMethod) {
		this.threshingMethod = threshingMethod;
	}

	public String getCleaningDate() {
		return cleaningDate;
	}

	public void setCleaningDate(String cleaningDate) {
		this.cleaningDate = cleaningDate;
	}

	public String getMoisture() {
		return moisture;
	}

	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	public String getPackingMaterial() {
		return packingMaterial;
	}

	public void setPackingMaterial(String packingMaterial) {
		this.packingMaterial = packingMaterial;
	}

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	public String getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(String numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getEmptyWeight() {
		return emptyWeight;
	}

	public void setEmptyWeight(String emptyWeight) {
		this.emptyWeight = emptyWeight;
	}

	public String getNettWeight() {
		return nettWeight;
	}

	public void setNettWeight(String nettWeight) {
		this.nettWeight = nettWeight;
	}

	public String getLabelled() {
		return labelled;
	}

	public void setLabelled(String labelled) {
		this.labelled = labelled;
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