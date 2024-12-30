package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestQualityStandards;

public interface HarvestQualityStandardRepository extends JpaRepository<HarvestQualityStandards, Long> {

	// Returning data
	List<HarvestQualityStandards> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestQualityStandards> findByProjectsIn(List<FarmProjects> projects);
}
