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
public class HarvestPurchasePointEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    private String purchasePoint;

    private String fieldExecutiveName;

    private String ppcSlipNo;

    private String dateOfReceipt;

    private String qualityReportNo;

    private String dateOfTestReport;

    private String labName;

    // Project association
    @ManyToOne
    private FarmProjects projects;

    // Audit fields
    @ManyToOne
    @JoinColumn(name = "created_by_pk1", updatable = false)
    private Users createdBy;

    @Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
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

    public String getPurchasePoint() {
        return purchasePoint;
    }

    public void setPurchasePoint(String purchasePoint) {
        this.purchasePoint = purchasePoint;
    }

    public String getFieldExecutiveName() {
        return fieldExecutiveName;
    }

    public void setFieldExecutiveName(String fieldExecutiveName) {
        this.fieldExecutiveName = fieldExecutiveName;
    }

    public String getPpcSlipNo() {
        return ppcSlipNo;
    }

    public void setPpcSlipNo(String ppcSlipNo) {
        this.ppcSlipNo = ppcSlipNo;
    }

    public String getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(String dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public String getQualityReportNo() {
        return qualityReportNo;
    }

    public void setQualityReportNo(String qualityReportNo) {
        this.qualityReportNo = qualityReportNo;
    }

    public String getDateOfTestReport() {
        return dateOfTestReport;
    }

    public void setDateOfTestReport(String dateOfTestReport) {
        this.dateOfTestReport = dateOfTestReport;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
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
