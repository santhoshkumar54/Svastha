package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestPriceConfirmation;

public interface HarvestPriceConfirmationRepository extends JpaRepository<HarvestPriceConfirmation, Long> {

	// Returning data
	List<HarvestPriceConfirmation> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestPriceConfirmation> findByProjectsIn(List<FarmProjects> projects);
}
