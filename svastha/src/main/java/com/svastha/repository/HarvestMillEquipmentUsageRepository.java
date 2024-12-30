package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestDryingProcess;
import com.svastha.entity.HarvestMillEquipmentUsage;

public interface HarvestMillEquipmentUsageRepository extends JpaRepository<HarvestMillEquipmentUsage, Long> {

	// Returning data
	List<HarvestMillEquipmentUsage> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestMillEquipmentUsage> findByProjectsIn(List<FarmProjects> projects);
}
