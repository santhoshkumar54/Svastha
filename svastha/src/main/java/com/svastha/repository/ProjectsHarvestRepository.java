package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectHarvest;

public interface ProjectsHarvestRepository extends JpaRepository<ProjectHarvest, Long> {

	ProjectHarvest findByProjects(FarmProjects projects);

	List<ProjectHarvest> findByProjectsIn(List<FarmProjects> projects);
}
