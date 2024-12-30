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
public class HarvestEwayBill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String eWayBillSystem;

	private String eWayBillNo;

	private String eWayBillDate;

	private String generatedBy;

	private String validFrom;

	private String validUntil;

	private String gstinOfSupplier;

	private String placeOfDispatchA;

	private String gstinOfRecipient;

	private String placeOfDelivery;

	private String documentNo;

	private String documentDate;

	private String transactionType;

	private String valueOfGoods;

	private String hsnCode;

	private String reasonForTransportation;

	private String transporter;

	private String mode;

	private String vehicleNo;

	private String placeOfDispatchB;

	private String enteredDate;

	private String enteredBy;

	private String cewbNo;

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

	public String getEWayBillSystem() {
		return eWayBillSystem;
	}

	public void setEWayBillSystem(String eWayBillSystem) {
		this.eWayBillSystem = eWayBillSystem;
	}

	public String getEWayBillNo() {
		return eWayBillNo;
	}

	public void setEWayBillNo(String eWayBillNo) {
		this.eWayBillNo = eWayBillNo;
	}

	public String getEWayBillDate() {
		return eWayBillDate;
	}

	public void setEWayBillDate(String eWayBillDate) {
		this.eWayBillDate = eWayBillDate;
	}

	public String getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
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

	public String geteWayBillSystem() {
		return eWayBillSystem;
	}

	public void seteWayBillSystem(String eWayBillSystem) {
		this.eWayBillSystem = eWayBillSystem;
	}

	public String geteWayBillNo() {
		return eWayBillNo;
	}

	public void seteWayBillNo(String eWayBillNo) {
		this.eWayBillNo = eWayBillNo;
	}

	public String geteWayBillDate() {
		return eWayBillDate;
	}

	public void seteWayBillDate(String eWayBillDate) {
		this.eWayBillDate = eWayBillDate;
	}

	public String getGstinOfSupplier() {
		return gstinOfSupplier;
	}

	public void setGstinOfSupplier(String gstinOfSupplier) {
		this.gstinOfSupplier = gstinOfSupplier;
	}

	public String getPlaceOfDispatchA() {
		return placeOfDispatchA;
	}

	public void setPlaceOfDispatchA(String placeOfDispatchA) {
		this.placeOfDispatchA = placeOfDispatchA;
	}

	public String getGstinOfRecipient() {
		return gstinOfRecipient;
	}

	public void setGstinOfRecipient(String gstinOfRecipient) {
		this.gstinOfRecipient = gstinOfRecipient;
	}

	public String getPlaceOfDelivery() {
		return placeOfDelivery;
	}

	public void setPlaceOfDelivery(String placeOfDelivery) {
		this.placeOfDelivery = placeOfDelivery;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getValueOfGoods() {
		return valueOfGoods;
	}

	public void setValueOfGoods(String valueOfGoods) {
		this.valueOfGoods = valueOfGoods;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getReasonForTransportation() {
		return reasonForTransportation;
	}

	public void setReasonForTransportation(String reasonForTransportation) {
		this.reasonForTransportation = reasonForTransportation;
	}

	public String getTransporter() {
		return transporter;
	}

	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getPlaceOfDispatchB() {
		return placeOfDispatchB;
	}

	public void setPlaceOfDispatchB(String placeOfDispatchB) {
		this.placeOfDispatchB = placeOfDispatchB;
	}

	public String getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(String enteredDate) {
		this.enteredDate = enteredDate;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public String getCewbNo() {
		return cewbNo;
	}

	public void setCewbNo(String cewbNo) {
		this.cewbNo = cewbNo;
	}

}
