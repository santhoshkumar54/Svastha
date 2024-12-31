package com.svastha.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class CarbonAwdMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    @ManyToOne
    @JoinColumn(name = "projects_pk1")
    private FarmProjects projects;

    @ManyToOne
    @JoinColumn(name = "device_pk1")
    private AwdDevice device;

    @ManyToOne
    @JoinColumn(name = "created_by_pk1", updatable = false)
    private Users createdBy;

    @Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
    private Timestamp createdDt;

    @ManyToOne
    private Users updatedBy;

    private Timestamp updatedDt;
}
