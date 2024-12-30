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
public class HarvestGstInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String invoiceNumber;

	private String invoiceDate;

	private String transportMode;

	private String vehicleNumber;

	private String billFromName;

	private String gstin;

	private String address;

	private String supplierContactNumber;

	private String millName;

	private String millAddress;

	private String gstinOfMill;

	private String recepientContactNumber;

	private String dispatchDateTime;

	private String placeOfDispatch;

	private String placeOfDelivery;

	private String location;

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

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
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

	public String getBillFromName() {
		return billFromName;
	}

	public void setBillFromName(String billFromName) {
		this.billFromName = billFromName;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSupplierContactNumber() {
		return supplierContactNumber;
	}

	public void setSupplierContactNumber(String supplierContactNumber) {
		this.supplierContactNumber = supplierContactNumber;
	}

	public String getMillName() {
		return millName;
	}

	public void setMillName(String millName) {
		this.millName = millName;
	}

	public String getMillAddress() {
		return millAddress;
	}

	public void setMillAddress(String millAddress) {
		this.millAddress = millAddress;
	}

	public String getGstinOfMill() {
		return gstinOfMill;
	}

	public void setGstinOfMill(String gstinOfMill) {
		this.gstinOfMill = gstinOfMill;
	}

	public String getRecepientContactNumber() {
		return recepientContactNumber;
	}

	public void setRecepientContactNumber(String recepientContactNumber) {
		this.recepientContactNumber = recepientContactNumber;
	}

	public String getDispatchDateTime() {
		return dispatchDateTime;
	}

	public void setDispatchDateTime(String dispatchDateTime) {
		this.dispatchDateTime = dispatchDateTime;
	}

	public String getPlaceOfDispatch() {
		return placeOfDispatch;
	}

	public void setPlaceOfDispatch(String placeOfDispatch) {
		this.placeOfDispatch = placeOfDispatch;
	}

	public String getPlaceOfDelivery() {
		return placeOfDelivery;
	}

	public void setPlaceOfDelivery(String placeOfDelivery) {
		this.placeOfDelivery = placeOfDelivery;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
