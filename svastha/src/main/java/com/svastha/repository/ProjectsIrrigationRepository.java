package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectIrrigation;

public interface ProjectsIrrigationRepository extends JpaRepository<ProjectIrrigation, Long> {

	ProjectIrrigation findAllIrrigationsByProjects(FarmProjects projects);

}
