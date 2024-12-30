package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestPackagingDetails;

public interface HarvestPackagingDetailsRepository extends JpaRepository<HarvestPackagingDetails, Long> {

	// Returning data
	List<HarvestPackagingDetails> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestPackagingDetails> findByProjectsIn(List<FarmProjects> projects);
}
