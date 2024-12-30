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
public class HarvestLoadingSlip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String slipNo;

	private String ratePerKg;

	private String weighmentSlipNo;

	private String quantityLoadedKg;

	private String noOfBags;

	private String totalValue;

	private String dateOfDispatch;

	private String dispatchTime;

	private String fromLocation;

	private String toLocation;

	private String vehicleNo;

	private String verifierName;

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

	public String getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}

	public String getRatePerKg() {
		return ratePerKg;
	}

	public void setRatePerKg(String ratePerKg) {
		this.ratePerKg = ratePerKg;
	}

	public String getWeighmentSlipNo() {
		return weighmentSlipNo;
	}

	public void setWeighmentSlipNo(String weighmentSlipNo) {
		this.weighmentSlipNo = weighmentSlipNo;
	}

	public String getQuantityLoadedKg() {
		return quantityLoadedKg;
	}

	public void setQuantityLoadedKg(String quantityLoadedKg) {
		this.quantityLoadedKg = quantityLoadedKg;
	}

	public String getNoOfBags() {
		return noOfBags;
	}

	public void setNoOfBags(String noOfBags) {
		this.noOfBags = noOfBags;
	}

	public String getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}

	public String getDateOfDispatch() {
		return dateOfDispatch;
	}

	public void setDateOfDispatch(String dateOfDispatch) {
		this.dateOfDispatch = dateOfDispatch;
	}

	public String getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(String dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVerifierName() {
		return verifierName;
	}

	public void setVerifierName(String verifierName) {
		this.verifierName = verifierName;
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
