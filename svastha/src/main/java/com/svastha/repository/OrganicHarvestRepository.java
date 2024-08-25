package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicHarvest;

public interface OrganicHarvestRepository extends JpaRepository<OrganicHarvest, Long> {

	OrganicHarvest findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicHarvest> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicHarvest> findByProjects(FarmProjects projects);
}
