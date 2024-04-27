package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicGrowthPromoter;

public interface OrganicGrowthPromoterRepository extends JpaRepository<OrganicGrowthPromoter, Long> {

	List<OrganicGrowthPromoter> findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicGrowthPromoter> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicGrowthPromoter> findByProjects(FarmProjects projects);

}
