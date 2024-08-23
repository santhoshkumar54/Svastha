package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectSyntheticFertilizers;

public interface ProjectsSyntheticFertilizerRepository extends JpaRepository<ProjectSyntheticFertilizers, Long> {

	List<ProjectSyntheticFertilizers> findAllByProjects(FarmProjects projects);

	List<ProjectSyntheticFertilizers> findByProjectsIn(List<FarmProjects> projects);

	List<ProjectSyntheticFertilizers> findByProjectsAndPlot(FarmProjects projects,FarmPlots plot);
}
