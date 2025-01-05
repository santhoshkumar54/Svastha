package com.svastha.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class HarvestEstimates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    @ManyToOne
    private FarmProjects projects;

    private String estimatedYield;

    private String estimatedDate;

    private String remarks;

    @Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
    private Timestamp createdDt;
}
