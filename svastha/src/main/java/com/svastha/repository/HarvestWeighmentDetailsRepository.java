package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestWeighmentDetails;

public interface HarvestWeighmentDetailsRepository extends JpaRepository<HarvestWeighmentDetails, Long> {

	// Returning data
	List<HarvestWeighmentDetails> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestWeighmentDetails> findByProjectsIn(List<FarmProjects> projects);
}
