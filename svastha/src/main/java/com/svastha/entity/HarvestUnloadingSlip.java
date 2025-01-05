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
public class HarvestUnloadingSlip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String unloadingReceiptNo;

	private String vehicleNo;

	private String dateOfUnloading;

	private String timeOfUnloading;

	private String dryerCode;

	private String unloadingLocation;

	private String loadingSlipNo;

	private String weighmentSlipNo;

	private String totalWeightKg;

	private String weightDifference;

	private String numberOfBags;

	private String moistureContent;

	private String remarksOfInCharge;

	private String nameOfInCharge;

	// To identify the project of the data.
	@ManyToOne
	private FarmProjects projects;

	// Common logging columns.
	@ManyToOne
	@JoinColumn(name = "created_by_pk1", updatable = false)
	private Users createdBy;

	@Column(name = "created_dt", updatable = false, insertable = false)
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

	public String getUnloadingReceiptNo() {
		return unloadingReceiptNo;
	}

	public void setUnloadingReceiptNo(String unloadingReceiptNo) {
		this.unloadingReceiptNo = unloadingReceiptNo;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getDateOfUnloading() {
		return dateOfUnloading;
	}

	public void setDateOfUnloading(String dateOfUnloading) {
		this.dateOfUnloading = dateOfUnloading;
	}

	public String getTimeOfUnloading() {
		return timeOfUnloading;
	}

	public void setTimeOfUnloading(String timeOfUnloading) {
		this.timeOfUnloading = timeOfUnloading;
	}

	public String getDryerCode() {
		return dryerCode;
	}

	public void setDryerCode(String dryerCode) {
		this.dryerCode = dryerCode;
	}

	public String getUnloadingLocation() {
		return unloadingLocation;
	}

	public void setUnloadingLocation(String unloadingLocation) {
		this.unloadingLocation = unloadingLocation;
	}

	public String getLoadingSlipNo() {
		return loadingSlipNo;
	}

	public void setLoadingSlipNo(String loadingSlipNo) {
		this.loadingSlipNo = loadingSlipNo;
	}

	public String getWeighmentSlipNo() {
		return weighmentSlipNo;
	}

	public void setWeighmentSlipNo(String weighmentSlipNo) {
		this.weighmentSlipNo = weighmentSlipNo;
	}

	public String getTotalWeightKg() {
		return totalWeightKg;
	}

	public void setTotalWeightKg(String totalWeightKg) {
		this.totalWeightKg = totalWeightKg;
	}

	public String getWeightDifference() {
		return weightDifference;
	}

	public void setWeightDifference(String weightDifference) {
		this.weightDifference = weightDifference;
	}

	public String getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(String numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public String getMoistureContent() {
		return moistureContent;
	}

	public void setMoistureContent(String moistureContent) {
		this.moistureContent = moistureContent;
	}

	public String getRemarksOfInCharge() {
		return remarksOfInCharge;
	}

	public void setRemarksOfInCharge(String remarksOfInCharge) {
		this.remarksOfInCharge = remarksOfInCharge;
	}

	public String getNameOfInCharge() {
		return nameOfInCharge;
	}

	public void setNameOfInCharge(String nameOfInCharge) {
		this.nameOfInCharge = nameOfInCharge;
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
