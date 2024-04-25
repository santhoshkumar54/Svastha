package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicWaterAnalysis;

@Repository
public interface OrganicWaterAnalysisRepository extends JpaRepository<OrganicWaterAnalysis, Long> {

	OrganicWaterAnalysis findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicWaterAnalysis> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicWaterAnalysis> findByProjects(FarmProjects projects);

}
