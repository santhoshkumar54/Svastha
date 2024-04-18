package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicSoilAnalysis;

public interface OrganicSoilAnalysisRepository extends JpaRepository<OrganicSoilAnalysis, Long> {

	OrganicSoilAnalysis findByProjectsAndPlots(FarmProjects projects,FarmPlots plots);

	List<OrganicSoilAnalysis> findByProjectsIn(List<FarmProjects> projects);
	
	List<OrganicSoilAnalysis> findByProjects(FarmProjects projects);

}
