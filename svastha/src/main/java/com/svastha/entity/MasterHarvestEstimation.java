package com.svastha.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class MasterHarvestEstimation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk1;

    @ManyToOne
    private MasterSeason masterSeason;

    @ManyToOne
    private MasterCropVariety cropVariety;

    private String duration;

    private String yieldPerAcre;

    @ManyToOne
    @JoinColumn(name = "created_by_pk1", updatable = false)
    private Users createdBy;

    @Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
    private Timestamp createdDt;

    @ManyToOne
    private Users updatedBy;

    private Timestamp updatedDt;
}
