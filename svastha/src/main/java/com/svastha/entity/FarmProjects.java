package com.svastha.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted = false")
public class FarmProjects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	@ManyToOne
	private Farms farm;

	@ManyToOne
	private MasterYear year;

	@ManyToOne
	private MasterSeason season;

	@ManyToOne
	private MasterCrop crop;

	@ManyToOne
	private MasterIcs ics;

	@ManyToOne
	private MasterCropVariety variety;

	private String sowingDate;

	// Mapping to the other table
	@OneToMany(cascade = CascadeType.ALL)
	private Set<ProjectPlots> plots;

	private String completion;

	public Set<ProjectPlots> getPlots() {
		return plots;
	}

	public void setPlots(Set<ProjectPlots> plots) {
		this.plots = plots;
	}

	private String status;

	private boolean isDisabled;

	private String remarks;

	@ManyToOne
	@JoinColumn(name = "created_by_pk1", updatable = false)
	private Users createdBy;

	@Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	@ManyToOne
	private MasterProjectType projectType;

	private boolean deleted = false;

	@ManyToOne
	private Users deletedBy;

	private Timestamp deletedDt;

	@ManyToOne
	private Users assignedTo;

	@ManyToOne
	private Users AssignedBy;

	private Timestamp assignedDt;

	private String location;

	@ManyToOne
	private RouteMaster routes;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public Farms getFarm() {
		return farm;
	}

	public void setFarm(Farms farm) {
		this.farm = farm;
	}

	public MasterYear getYear() {
		return year;
	}

	public void setYear(MasterYear year) {
		this.year = year;
	}

	public MasterSeason getSeason() {
		return season;
	}

	public void setSeason(MasterSeason season) {
		this.season = season;
	}

	public MasterCrop getCrop() {
		return crop;
	}

	public void setCrop(MasterCrop crop) {
		this.crop = crop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getCompletion() {
		return completion;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
	}

	public MasterProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(MasterProjectType projectType) {
		this.projectType = projectType;
	}

	public MasterIcs getIcs() {
		return ics;
	}

	public void setIcs(MasterIcs ics) {
		this.ics = ics;
	}

	public MasterCropVariety getVariety() {
		return variety;
	}

	public void setVariety(MasterCropVariety variety) {
		this.variety = variety;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Users getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(Users deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Timestamp getDeletedDt() {
		return deletedDt;
	}

	public void setDeletedDt(Timestamp deletedDt) {
		this.deletedDt = deletedDt;
	}

	public Users getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Users assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Users getAssignedBy() {
		return AssignedBy;
	}

	public void setAssignedBy(Users assignedBy) {
		AssignedBy = assignedBy;
	}

	public Timestamp getAssignedDt() {
		return assignedDt;
	}

	public void setAssignedDt(Timestamp assignedDt) {
		this.assignedDt = assignedDt;
	}

	public String getSowingDate() {
		return sowingDate;
	}

	public void setSowingDate(String sowingDate) {
		this.sowingDate = sowingDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@ManyToOne
	@JoinColumn(name = "epic_route_pk1")
	private EpicRoute epicRoute;

	public EpicRoute getEpicRoute() {
		return epicRoute;
	}

	public void setEpicRoute(EpicRoute epicRoute) {
		this.epicRoute = epicRoute;
	}

}