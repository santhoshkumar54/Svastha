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
public class HarvestInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String invoiceNumber;

	private String dateOfInvoice;

	private String purchasePointAddress;

	private String truckNo;

	private String emptyWeight;

	private String grossWeight;

	private String netWeight;

	private String numberOfBags;

	private String hsnCode;

	private String buyerName;

	private String billingAddress;

	private String billingGSTIN;

	private String deliveryAddress;

	private String deliveryGSTIN;

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

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getDateOfInvoice() {
		return dateOfInvoice;
	}

	public void setDateOfInvoice(String dateOfInvoice) {
		this.dateOfInvoice = dateOfInvoice;
	}

	public String getPurchasePointAddress() {
		return purchasePointAddress;
	}

	public void setPurchasePointAddress(String purchasePointAddress) {
		this.purchasePointAddress = purchasePointAddress;
	}

	public String getTruckNo() {
		return truckNo;
	}

	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}

	public String getEmptyWeight() {
		return emptyWeight;
	}

	public void setEmptyWeight(String emptyWeight) {
		this.emptyWeight = emptyWeight;
	}

	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public String getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(String numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
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

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingGSTIN() {
		return billingGSTIN;
	}

	public void setBillingGSTIN(String billingGSTIN) {
		this.billingGSTIN = billingGSTIN;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryGSTIN() {
		return deliveryGSTIN;
	}

	public void setDeliveryGSTIN(String deliveryGSTIN) {
		this.deliveryGSTIN = deliveryGSTIN;
	}
}
