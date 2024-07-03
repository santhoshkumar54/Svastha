package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicHarvestEstimates;

public interface OrganicHarvestEstimatesRepository extends JpaRepository<OrganicHarvestEstimates, Long>{

	OrganicHarvestEstimates findByProject(FarmProjects project);
	
	List<OrganicHarvestEstimates> findByEstimatedYield(String yield);
}
