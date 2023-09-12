package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LandDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pk1;

	private String ownership;

	private String ownerName;

	private String ownerAddress;

	private String leasingFrom;

	private String areaAcres;

	private String surveyNumber;

	private String pattaNumber;

	private String soilData;
	
	private String location;
	
	@ManyToOne
	private Farms farm;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	public Integer getPk1() {
		return pk1;
	}

	public void setPk1(Integer pk1) {
		this.pk1 = pk1;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getLeasingFrom() {
		return leasingFrom;
	}

	public void setLeasingFrom(String leasingFrom) {
		this.leasingFrom = leasingFrom;
	}

	public String getAreaAcres() {
		return areaAcres;
	}

	public void setAreaAcres(String areaAcres) {
		this.areaAcres = areaAcres;
	}

	public String getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(String surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public String getPattaNumber() {
		return pattaNumber;
	}

	public void setPattaNumber(String pattaNumber) {
		this.pattaNumber = pattaNumber;
	}

	public String getSoilData() {
		return soilData;
	}

	public void setSoilData(String soilData) {
		this.soilData = soilData;
	}

	public Farms getFarm() {
		return farm;
	}

	public void setFarm(Farms farm) {
		this.farm = farm;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}