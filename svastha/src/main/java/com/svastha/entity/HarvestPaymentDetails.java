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
public class HarvestPaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String farmerPrice;

	private String labourCharges;

	private String priceNegotiation;

	private String currentPriceTrends;

	private String pricePerMT;

	private String paymentFollowUpPerson;

	private String approvalStatus;

	private String nameOfSender;

	private String amount;

	private String paymentMode;

	private String utrReferenceNumber;

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

	public String getFarmerPrice() {
		return farmerPrice;
	}

	public void setFarmerPrice(String farmerPrice) {
		this.farmerPrice = farmerPrice;
	}

	public String getLabourCharges() {
		return labourCharges;
	}

	public void setLabourCharges(String labourCharges) {
		this.labourCharges = labourCharges;
	}

	public String getPriceNegotiation() {
		return priceNegotiation;
	}

	public void setPriceNegotiation(String priceNegotiation) {
		this.priceNegotiation = priceNegotiation;
	}

	public String getCurrentPriceTrends() {
		return currentPriceTrends;
	}

	public void setCurrentPriceTrends(String currentPriceTrends) {
		this.currentPriceTrends = currentPriceTrends;
	}

	public String getPricePerMT() {
		return pricePerMT;
	}

	public void setPricePerMT(String pricePerMT) {
		this.pricePerMT = pricePerMT;
	}

	public String getPaymentFollowUpPerson() {
		return paymentFollowUpPerson;
	}

	public void setPaymentFollowUpPerson(String paymentFollowUpPerson) {
		this.paymentFollowUpPerson = paymentFollowUpPerson;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getNameOfSender() {
		return nameOfSender;
	}

	public void setNameOfSender(String nameOfSender) {
		this.nameOfSender = nameOfSender;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getUtrReferenceNumber() {
		return utrReferenceNumber;
	}

	public void setUtrReferenceNumber(String utrReferenceNumber) {
		this.utrReferenceNumber = utrReferenceNumber;
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
