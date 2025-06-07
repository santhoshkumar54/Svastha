
package com.svastha.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class RouteAssignmentTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    @ManyToOne
    @JoinColumn(name = "epic_route_pk1")
    private EpicRoute epicRoute;

    @ManyToOne
    @JoinColumn(name = "assigned_to_pk1")
    private Users assignedTo;

    private Integer sprintDay; // Day within the sprint (1 to sprintSize)

    @ManyToOne
    @JoinColumn(name = "created_by_pk1")
    private Users createdBy;

    @Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
    private Timestamp createdDt;

    // Getters and Setters
    public Long getPk1() {
        return pk1;
    }

    public void setPk1(Long pk1) {
        this.pk1 = pk1;
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

    public Integer getSprintDay() {
        return sprintDay;
    }

    public void setSprintDay(Integer sprintDay) {
        this.sprintDay = sprintDay;
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
}
