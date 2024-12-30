package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestStocking;

public interface HarvestStockingRepository extends JpaRepository<HarvestStocking, Long> {

	// Returning data
	List<HarvestStocking> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestStocking> findByProjectsIn(List<FarmProjects> projects);
}
