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
public class HarvestPriceConfirmation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String qty;

	private String purchaseOrder;

	private String priceTrends;

	private String rate;

	private String rateBag;

	private String qualityVerifedBy;

	private String qualityStaff;

	private String slipPreparedBy;

	private String approvedBy;

	private String dateOfApproval;

	// to identify the project of the data.
	@ManyToOne
	private FarmProjects projects;

	// common logging coluumns.
	@ManyToOne
	@JoinColumn(name = "created_by_pk1", updatable = false)
	private Users createdBy;

	// Need to add default values in mysql manually. else it will fail to insert.
	@Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
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

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getPriceTrends() {
		return priceTrends;
	}

	public void setPriceTrends(String priceTrends) {
		this.priceTrends = priceTrends;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getRateBag() {
		return rateBag;
	}

	public void setRateBag(String rateBag) {
		this.rateBag = rateBag;
	}

	public String getQualityVerifedBy() {
		return qualityVerifedBy;
	}

	public void setQualityVerifedBy(String qualityVerifedBy) {
		this.qualityVerifedBy = qualityVerifedBy;
	}

	public String getQualityStaff() {
		return qualityStaff;
	}

	public void setQualityStaff(String qualityStaff) {
		this.qualityStaff = qualityStaff;
	}

	public String getSlipPreparedBy() {
		return slipPreparedBy;
	}

	public void setSlipPreparedBy(String slipPreparedBy) {
		this.slipPreparedBy = slipPreparedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getDateOfApproval() {
		return dateOfApproval;
	}

	public void setDateOfApproval(String dateOfApproval) {
		this.dateOfApproval = dateOfApproval;
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