package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestPurchasePointEntry;

public interface HarvestPurchasePointEntryRepository extends JpaRepository<HarvestPurchasePointEntry, Long> {

	// Returning data
	List<HarvestPurchasePointEntry> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestPurchasePointEntry> findByProjectsIn(List<FarmProjects> projects);
}
