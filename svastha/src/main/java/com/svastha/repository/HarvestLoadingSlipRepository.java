package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestLoadingSlip;

public interface HarvestLoadingSlipRepository extends JpaRepository<HarvestLoadingSlip, Long> {

	// Returning data
	List<HarvestLoadingSlip> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestLoadingSlip> findByProjectsIn(List<FarmProjects> projects);
}
