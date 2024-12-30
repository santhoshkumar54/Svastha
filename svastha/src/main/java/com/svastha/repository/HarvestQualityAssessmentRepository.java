package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestQualityAssessment;

public interface HarvestQualityAssessmentRepository extends JpaRepository<HarvestQualityAssessment, Long> {

	// Returning data
	List<HarvestQualityAssessment> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestQualityAssessment> findByProjectsIn(List<FarmProjects> projects);
}
