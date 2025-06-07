
package com.svastha.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class EpicRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    @ManyToOne
    @JoinColumn(name = "epic_pk1")
    private Epic epic;

    private String routeName;
    private Integer projectCount; // should be 8 max

    @OneToMany(mappedBy = "epicRoute", fetch = FetchType.LAZY)
    private List<FarmProjects> projects;

    @ManyToOne
    @JoinColumn(name = "created_by_pk1", updatable = false)
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

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public List<FarmProjects> getProjects() {
        return projects;
    }

    public void setProjects(List<FarmProjects> projects) {
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
}
