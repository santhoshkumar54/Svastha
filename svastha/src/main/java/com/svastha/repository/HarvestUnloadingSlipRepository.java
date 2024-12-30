package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestUnloadingSlip;

public interface HarvestUnloadingSlipRepository extends JpaRepository<HarvestUnloadingSlip, Long> {

	// Returning data
	List<HarvestUnloadingSlip> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestUnloadingSlip> findByProjectsIn(List<FarmProjects> projects);
}
