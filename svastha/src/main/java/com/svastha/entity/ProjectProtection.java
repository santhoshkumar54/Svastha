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
public class ProjectProtection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

    @ManyToOne
    private MasterPests pests;
    
    @ManyToOne
    private MasterChemicals chemicals;
    
    @ManyToOne
    private MasterChemicalBrands brands;
    
    @ManyToOne
    private FarmPlots plots;
    
	private String appliedDose;

	private String appliedDate;

	private String appliedMethod;

	private String appliedMode;
	
	@ManyToOne
	private MasterCropStage stage;
	
	private String compliant;
	
	private String approvalStatus;
	
	private String reason;
	
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "created_by_pk1", updatable = false)
	private Users createdBy;

	@Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
	private Timestamp createdDt;
	
	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	@ManyToOne
	private FarmProjects projects;

	private String cost;

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public FarmProjects getProjects() {
		return projects;
	}

	public void setProjects(FarmProjects projects) {
		this.projects = projects;
	}

	public MasterPests getPests() {
		return pests;
	}

	public void setPests(MasterPests pests) {
		this.pests = pests;
	}

	public MasterChemicals getChemicals() {
		return chemicals;
	}

	public void setChemicals(MasterChemicals chemicals) {
		this.chemicals = chemicals;
	}

	public MasterChemicalBrands getBrands() {
		return brands;
	}

	public void setBrands(MasterChemicalBrands brands) {
		this.brands = brands;
	}

	public FarmPlots getPlots() {
		return plots;
	}

	public void setPlots(FarmPlots plots) {
		this.plots = plots;
	}

	public String getAppliedDose() {
		return appliedDose;
	}

	public void setAppliedDose(String appliedDose) {
		this.appliedDose = appliedDose;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getAppliedMethod() {
		return appliedMethod;
	}

	public void setAppliedMethod(String appliedMethod) {
		this.appliedMethod = appliedMethod;
	}

	public String getAppliedMode() {
		return appliedMode;
	}

	public void setAppliedMode(String appliedMode) {
		this.appliedMode = appliedMode;
	}

	public MasterCropStage getStage() {
		return stage;
	}

	public void setStage(MasterCropStage stage) {
		this.stage = stage;
	}

	public String getCompliant() {
		return compliant;
	}

	public void setCompliant(String compliant) {
		this.compliant = compliant;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}