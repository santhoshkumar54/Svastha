package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectPlots;

public interface ProjectsPlotsRepository extends JpaRepository<ProjectPlots, Long> {

	List<ProjectPlots> findAllPlotsByProject(FarmProjects projects);

}
