package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicPlotBoundary;

public interface OrganicPlotBoundaryRepository extends JpaRepository<OrganicPlotBoundary, Long> {

	List<OrganicPlotBoundary> findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicPlotBoundary> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicPlotBoundary> findByProjects(FarmProjects projects);

}
