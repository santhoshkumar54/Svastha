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
public class HarvestStocking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String stackNumber;

	private String lotCode;

	private String numberOfLot;

	private String numberOfBagsInLot;

	private String identificationMark;

	private String tarpaulinUsed;

	private String fumigationChemical;

	private String dose;

	private String fumigationDate;

	private String exposureDate;

	private String agencyName;

	private String remarks;

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

	public String getStackNumber() {
		return stackNumber;
	}

	public void setStackNumber(String stackNumber) {
		this.stackNumber = stackNumber;
	}

	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}

	public String getNumberOfLot() {
		return numberOfLot;
	}

	public void setNumberOfLot(String numberOfLot) {
		this.numberOfLot = numberOfLot;
	}

	public String getNumberOfBagsInLot() {
		return numberOfBagsInLot;
	}

	public void setNumberOfBagsInLot(String numberOfBagsInLot) {
		this.numberOfBagsInLot = numberOfBagsInLot;
	}

	public String getIdentificationMark() {
		return identificationMark;
	}

	public void setIdentificationMark(String identificationMark) {
		this.identificationMark = identificationMark;
	}

	public String getTarpaulinUsed() {
		return tarpaulinUsed;
	}

	public void setTarpaulinUsed(String tarpaulinUsed) {
		this.tarpaulinUsed = tarpaulinUsed;
	}

	public String getFumigationChemical() {
		return fumigationChemical;
	}

	public void setFumigationChemical(String fumigationChemical) {
		this.fumigationChemical = fumigationChemical;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getFumigationDate() {
		return fumigationDate;
	}

	public void setFumigationDate(String fumigationDate) {
		this.fumigationDate = fumigationDate;
	}

	public String getExposureDate() {
		return exposureDate;
	}

	public void setExposureDate(String exposureDate) {
		this.exposureDate = exposureDate;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
