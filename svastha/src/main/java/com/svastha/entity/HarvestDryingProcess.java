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
public class HarvestDryingProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String inChargeName;

	private String startTime;

	private String endTime;

	private String binCapacity;

	private String dryingTotalTime;

	private String moistureBeforeDrying;

	private String moistureAfterDrying;

	private String binUnloadingDetails;

	private String numberOfTrucksUnloaded;

	private String truckCapacity;

	private String totalUnloadedCapacity;

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

	public String getInChargeName() {
		return inChargeName;
	}

	public void setInChargeName(String inChargeName) {
		this.inChargeName = inChargeName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBinCapacity() {
		return binCapacity;
	}

	public void setBinCapacity(String binCapacity) {
		this.binCapacity = binCapacity;
	}

	public String getDryingTotalTime() {
		return dryingTotalTime;
	}

	public void setDryingTotalTime(String dryingTotalTime) {
		this.dryingTotalTime = dryingTotalTime;
	}

	public String getMoistureBeforeDrying() {
		return moistureBeforeDrying;
	}

	public void setMoistureBeforeDrying(String moistureBeforeDrying) {
		this.moistureBeforeDrying = moistureBeforeDrying;
	}

	public String getMoistureAfterDrying() {
		return moistureAfterDrying;
	}

	public void setMoistureAfterDrying(String moistureAfterDrying) {
		this.moistureAfterDrying = moistureAfterDrying;
	}

	public String getBinUnloadingDetails() {
		return binUnloadingDetails;
	}

	public void setBinUnloadingDetails(String binUnloadingDetails) {
		this.binUnloadingDetails = binUnloadingDetails;
	}

	public String getNumberOfTrucksUnloaded() {
		return numberOfTrucksUnloaded;
	}

	public void setNumberOfTrucksUnloaded(String numberOfTrucksUnloaded) {
		this.numberOfTrucksUnloaded = numberOfTrucksUnloaded;
	}

	public String getTruckCapacity() {
		return truckCapacity;
	}

	public void setTruckCapacity(String truckCapacity) {
		this.truckCapacity = truckCapacity;
	}

	public String getTotalUnloadedCapacity() {
		return totalUnloadedCapacity;
	}

	public void setTotalUnloadedCapacity(String totalUnloadedCapacity) {
		this.totalUnloadedCapacity = totalUnloadedCapacity;
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
