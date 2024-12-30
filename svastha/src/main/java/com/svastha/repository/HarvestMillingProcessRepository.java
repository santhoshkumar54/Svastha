package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestMillingProcess;

public interface HarvestMillingProcessRepository extends JpaRepository<HarvestMillingProcess, Long> {

	// Returning data
	List<HarvestMillingProcess> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestMillingProcess> findByProjectsIn(List<FarmProjects> projects);
}
