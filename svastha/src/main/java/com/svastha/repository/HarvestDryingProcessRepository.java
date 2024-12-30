package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestDryingProcess;

public interface HarvestDryingProcessRepository extends JpaRepository<HarvestDryingProcess, Long> {

	// Returning data
	List<HarvestDryingProcess> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestDryingProcess> findByProjectsIn(List<FarmProjects> projects);
}
