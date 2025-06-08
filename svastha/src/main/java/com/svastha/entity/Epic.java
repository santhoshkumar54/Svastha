
package com.svastha.entity;

import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
public class Epic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    @ManyToOne
    @JoinColumn(name = "season_pk1")
    private MasterSeason season;

    @ManyToOne
    @JoinColumn(name = "variety_pk1")
    private MasterCropVariety variety;

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

  }
