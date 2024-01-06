package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectProcurement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	@ManyToOne
	private MasterCropVariety variety;

	private String purchaseDate;

	private String farmerCode;

	private String lotNumber;

	private String purchaseCenter;

	private String hr;

	private String moisture;

	private String broken;

	private String packSize;

	private String numberOfBags;

	private String length;

	private String breadth;

	private String dd;

	private String intert;

	private String admixture;

	private String cheff;

	private String dispatchDate;

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

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getFarmerCode() {
		return farmerCode;
	}

	public void setFarmerCode(String farmerCode) {
		this.farmerCode = farmerCode;
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

	public String getHr() {
		return hr;
	}

	public void setHr(String hr) {
		this.hr = hr;
	}

	public String getMoisture() {
		return moisture;
	}

	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	public String getBroken() {
		return broken;
	}

	public void setBroken(String broken) {
		this.broken = broken;
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

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getIntert() {
		return intert;
	}

	public void setIntert(String intert) {
		this.intert = intert;
	}

	public String getAdmixture() {
		return admixture;
	}

	public void setAdmixture(String admixture) {
		this.admixture = admixture;
	}

	public String getCheff() {
		return cheff;
	}

	public void setCheff(String cheff) {
		this.cheff = cheff;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
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