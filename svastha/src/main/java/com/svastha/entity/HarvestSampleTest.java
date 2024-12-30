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
public class HarvestSampleTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String stackNumber;

	private String location;

	private String product;

	private String quantity;

	private String sampleCode;

	private String samplingDate;

	private String samplePrepDate;

	private String sampleTestDate;

	private String reportDate;

	private String mrl; // Maximum Residue Limit

	private String result;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSampleCode() {
		return sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public String getSamplingDate() {
		return samplingDate;
	}

	public void setSamplingDate(String samplingDate) {
		this.samplingDate = samplingDate;
	}

	public String getSamplePrepDate() {
		return samplePrepDate;
	}

	public void setSamplePrepDate(String samplePrepDate) {
		this.samplePrepDate = samplePrepDate;
	}

	public String getSampleTestDate() {
		return sampleTestDate;
	}

	public void setSampleTestDate(String sampleTestDate) {
		this.sampleTestDate = sampleTestDate;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getMrl() {
		return mrl;
	}

	public void setMrl(String mrl) {
		this.mrl = mrl;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
