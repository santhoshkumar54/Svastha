package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectDispatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	@ManyToOne
	private MasterCropVariety variety;

	private String dispatchDate;

	private String invoiceNumber;

	private String truckNumber;

	private String truckBiltyNumber;

	private String farmerCode;

	private String numberOfBags;

	private String hsnCode;

	private String grossWeight;

	private String emptyWeight;

	private String nettWeight;

	private String ratePerKg;

	private String totalValue;

	private String dispatchFrom;

	private String dispatchTo;

	private String dispatchBy;

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

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getTruckNumber() {
		return truckNumber;
	}

	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}

	public String getTruckBiltyNumber() {
		return truckBiltyNumber;
	}

	public void setTruckBiltyNumber(String truckBiltyNumber) {
		this.truckBiltyNumber = truckBiltyNumber;
	}

	public String getFarmerCode() {
		return farmerCode;
	}

	public void setFarmerCode(String farmerCode) {
		this.farmerCode = farmerCode;
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

	public String getRatePerKg() {
		return ratePerKg;
	}

	public void setRatePerKg(String ratePerKg) {
		this.ratePerKg = ratePerKg;
	}

	public String getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}

	public String getDispatchFrom() {
		return dispatchFrom;
	}

	public void setDispatchFrom(String dispatchFrom) {
		this.dispatchFrom = dispatchFrom;
	}

	public String getDispatchTo() {
		return dispatchTo;
	}

	public void setDispatchTo(String dispatchTo) {
		this.dispatchTo = dispatchTo;
	}

	public String getDispatchBy() {
		return dispatchBy;
	}

	public void setDispatchBy(String dispatchBy) {
		this.dispatchBy = dispatchBy;
	}
}