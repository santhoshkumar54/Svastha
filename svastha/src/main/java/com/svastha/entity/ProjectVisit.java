
package com.svastha.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ProjectVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    @ManyToOne
    @JoinColumn(name = "farm_project_pk1")
    private FarmProjects farmProject;

    @ManyToOne
    @JoinColumn(name = "route_assignment_pk1")
    private RouteAssignment routeAssignment;

    @ManyToOne
    @JoinColumn(name = "visited_by_pk1")
    private Users visitedBy;

    private Timestamp checkInTime;
    private Double latitude;
    private Double longitude;
    private String remarks;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    @Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
    private Timestamp createdDt;

    // Getters and Setters
    public Long getPk1() {
        return pk1;
    }

    public void setPk1(Long pk1) {
        this.pk1 = pk1;
    }

    public FarmProjects getFarmProject() {
        return farmProject;
    }

    public void setFarmProject(FarmProjects farmProject) {
        this.farmProject = farmProject;
    }

    public RouteAssignment getRouteAssignment() {
        return routeAssignment;
    }

    public void setRouteAssignment(RouteAssignment routeAssignment) {
        this.routeAssignment = routeAssignment;
    }

    public Users getVisitedBy() {
        return visitedBy;
    }

    public void setVisitedBy(Users visitedBy) {
        this.visitedBy = visitedBy;
    }

    public Timestamp getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Timestamp checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }

    public Timestamp getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Timestamp createdDt) {
        this.createdDt = createdDt;
    }

    public enum VisitStatus {
        PENDING, COMPLETED, SKIPPED
    }
}
