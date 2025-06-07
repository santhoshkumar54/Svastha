package com.svastha.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class RouteAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    @ManyToOne
    @JoinColumn(name = "route_assignment_template_pk1")
    private RouteAssignmentTemplate routeAssignmentTemplate;

    private LocalDate assignedDate;
    private Integer sprintNumber;
    private Integer sprintDay; // Day within the sprint this assignment is for

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    public enum AssignmentStatus {
        ASSIGNED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    @ManyToOne
    @JoinColumn(name = "epic_route_pk1")
    private EpicRoute epicRoute;

    @ManyToOne
    @JoinColumn(name = "assigned_to_pk1")
    private Users assignedTo;

    @ManyToOne
    @JoinColumn(name = "assigned_by_pk1")
    private Users assignedBy;

    @Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
    private Timestamp createdDt;

    // Constructors
    public RouteAssignment() {}

    // Getters and Setters
    public Long getPk1() {
        return pk1;
    }

    public void setPk1(Long pk1) {
        this.pk1 = pk1;
    }

    public RouteAssignmentTemplate getRouteAssignmentTemplate() {
        return routeAssignmentTemplate;
    }

    public void setRouteAssignmentTemplate(RouteAssignmentTemplate routeAssignmentTemplate) {
        this.routeAssignmentTemplate = routeAssignmentTemplate;
    }

    public Integer getSprintDay() {
        return sprintDay;
    }

    public void setSprintDay(Integer sprintDay) {
        this.sprintDay = sprintDay;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Integer getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(Integer sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    public AssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }

    public EpicRoute getEpicRoute() {
        return epicRoute;
    }

    public void setEpicRoute(EpicRoute epicRoute) {
        this.epicRoute = epicRoute;
    }

    public Users getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Users assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Users getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Users assignedBy) {
        this.assignedBy = assignedBy;
    }

    public Timestamp getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Timestamp createdDt) {
        this.createdDt = createdDt;
    }
}