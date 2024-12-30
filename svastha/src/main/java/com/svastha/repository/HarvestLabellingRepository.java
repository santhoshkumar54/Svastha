package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestLabelling;

public interface HarvestLabellingRepository extends JpaRepository<HarvestLabelling, Long> {

	// Returning data
	List<HarvestLabelling> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestLabelling> findByProjectsIn(List<FarmProjects> projects);
}
