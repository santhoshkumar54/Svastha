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
public class HarvestMillingProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String loadArrivalDate;

	private String millAndLocation;

	private String loadNo;

	private String vehicleNo;

	private String paddyMoistureContent;

	private String batch;

	private String soakingDate;

	private String millingDate;

	private String inputQuantityKgs;

	private String fullRiceKgs;

	private String brokenRiceKgs;

	private String blackRiceKgs;

	private String dustRiceKgs;

	private String headRiceRecovery;

	private String totalRiceRecovery;

	private String outturnKgs;

	private String millingRecoveryPercentage;

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

	public String getLoadArrivalDate() {
		return loadArrivalDate;
	}

	public void setLoadArrivalDate(String loadArrivalDate) {
		this.loadArrivalDate = loadArrivalDate;
	}

	public String getMillAndLocation() {
		return millAndLocation;
	}

	public void setMillAndLocation(String millAndLocation) {
		this.millAndLocation = millAndLocation;
	}

	public String getLoadNo() {
		return loadNo;
	}

	public void setLoadNo(String loadNo) {
		this.loadNo = loadNo;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getPaddyMoistureContent() {
		return paddyMoistureContent;
	}

	public void setPaddyMoistureContent(String paddyMoistureContent) {
		this.paddyMoistureContent = paddyMoistureContent;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getSoakingDate() {
		return soakingDate;
	}

	public void setSoakingDate(String soakingDate) {
		this.soakingDate = soakingDate;
	}

	public String getMillingDate() {
		return millingDate;
	}

	public void setMillingDate(String millingDate) {
		this.millingDate = millingDate;
	}

	public String getInputQuantityKgs() {
		return inputQuantityKgs;
	}

	public void setInputQuantityKgs(String inputQuantityKgs) {
		this.inputQuantityKgs = inputQuantityKgs;
	}

	public String getFullRiceKgs() {
		return fullRiceKgs;
	}

	public void setFullRiceKgs(String fullRiceKgs) {
		this.fullRiceKgs = fullRiceKgs;
	}

	public String getBrokenRiceKgs() {
		return brokenRiceKgs;
	}

	public void setBrokenRiceKgs(String brokenRiceKgs) {
		this.brokenRiceKgs = brokenRiceKgs;
	}

	public String getBlackRiceKgs() {
		return blackRiceKgs;
	}

	public void setBlackRiceKgs(String blackRiceKgs) {
		this.blackRiceKgs = blackRiceKgs;
	}

	public String getDustRiceKgs() {
		return dustRiceKgs;
	}

	public void setDustRiceKgs(String dustRiceKgs) {
		this.dustRiceKgs = dustRiceKgs;
	}

	public String getHeadRiceRecovery() {
		return headRiceRecovery;
	}

	public void setHeadRiceRecovery(String headRiceRecovery) {
		this.headRiceRecovery = headRiceRecovery;
	}

	public String getTotalRiceRecovery() {
		return totalRiceRecovery;
	}

	public void setTotalRiceRecovery(String totalRiceRecovery) {
		this.totalRiceRecovery = totalRiceRecovery;
	}

	public String getOutturnKgs() {
		return outturnKgs;
	}

	public void setOutturnKgs(String outturnKgs) {
		this.outturnKgs = outturnKgs;
	}

	public String getMillingRecoveryPercentage() {
		return millingRecoveryPercentage;
	}

	public void setMillingRecoveryPercentage(String millingRecoveryPercentage) {
		this.millingRecoveryPercentage = millingRecoveryPercentage;
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
