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
public class HarvestWeighmentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String weighmentSlipNumber;

	private String dateTimeOfWeighment;

	private String loadingUnloadingStatus; // e.g., Pre-Loading/Post-Unloading

	private String vehicleNumber;

	private String driverName;

	private String grossWeight;

	private String tareWeight;

	private String netWeight;

	private String weightDifference;

	private String moisturePercentage;

	private String totalQuantity;

	private String numberOfBags;

	private String chargesForWeighment;

	private String nameOfWeighBridge;

	private String signatureOrSealOfInCharge;

	private String placeOfWeighment;

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

	public String getWeighmentSlipNumber() {
		return weighmentSlipNumber;
	}

	public void setWeighmentSlipNumber(String weighmentSlipNumber) {
		this.weighmentSlipNumber = weighmentSlipNumber;
	}

	public String getDateTimeOfWeighment() {
		return dateTimeOfWeighment;
	}

	public void setDateTimeOfWeighment(String dateTimeOfWeighment) {
		this.dateTimeOfWeighment = dateTimeOfWeighment;
	}

	public String getLoadingUnloadingStatus() {
		return loadingUnloadingStatus;
	}

	public void setLoadingUnloadingStatus(String loadingUnloadingStatus) {
		this.loadingUnloadingStatus = loadingUnloadingStatus;
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

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getTareWeight() {
		return tareWeight;
	}

	public void setTareWeight(String tareWeight) {
		this.tareWeight = tareWeight;
	}

	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public String getWeightDifference() {
		return weightDifference;
	}

	public void setWeightDifference(String weightDifference) {
		this.weightDifference = weightDifference;
	}

	public String getMoisturePercentage() {
		return moisturePercentage;
	}

	public void setMoisturePercentage(String moisturePercentage) {
		this.moisturePercentage = moisturePercentage;
	}

	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(String numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public String getChargesForWeighment() {
		return chargesForWeighment;
	}

	public void setChargesForWeighment(String chargesForWeighment) {
		this.chargesForWeighment = chargesForWeighment;
	}

	public String getNameOfWeighBridge() {
		return nameOfWeighBridge;
	}

	public void setNameOfWeighBridge(String nameOfWeighBridge) {
		this.nameOfWeighBridge = nameOfWeighBridge;
	}

	public String getSignatureOrSealOfInCharge() {
		return signatureOrSealOfInCharge;
	}

	public void setSignatureOrSealOfInCharge(String signatureOrSealOfInCharge) {
		this.signatureOrSealOfInCharge = signatureOrSealOfInCharge;
	}

	public String getPlaceOfWeighment() {
		return placeOfWeighment;
	}

	public void setPlaceOfWeighment(String placeOfWeighment) {
		this.placeOfWeighment = placeOfWeighment;
	}

}
