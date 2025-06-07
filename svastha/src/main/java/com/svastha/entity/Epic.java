
package com.svastha.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class Epic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    @ManyToOne
    @JoinColumn(name = "season_pk1")
    private MasterSeason season;

    @ManyToOne
    @JoinColumn(name = "variety_pk1")
    private MasterVariety variety;

    @ManyToOne
    @JoinColumn(name = "year_pk1")
    private MasterYear year;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer sprintSize; // frequency to revisit in days
    private Integer maxProjectsPerRoute; // maximum projects allowed per route (default 8)

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

    public MasterSeason getSeason() {
        return season;
    }

    public void setSeason(MasterSeason season) {
        this.season = season;
    }

    public MasterVariety getVariety() {
        return variety;
    }

    public void setVariety(MasterVariety variety) {
        this.variety = variety;
    }

    public MasterYear getYear() {
        return year;
    }

    public void setYear(MasterYear year) {
        this.year = year;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getSprintSize() {
        return sprintSize;
    }

    public void setSprintSize(Integer sprintSize) {
        this.sprintSize = sprintSize;
    }

    public Integer getMaxProjectsPerRoute() {
        return maxProjectsPerRoute;
    }

    public void setMaxProjectsPerRoute(Integer maxProjectsPerRoute) {
        this.maxProjectsPerRoute = maxProjectsPerRoute;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getSprintSize() {
        return sprintSize;
    }

    public void setSprintSize(Integer sprintSize) {
        this.sprintSize = sprintSize;
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
