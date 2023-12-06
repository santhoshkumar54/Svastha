package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectIrrigation;

public interface ProjectsIrrigationRepository extends JpaRepository<ProjectIrrigation, Long> {

	List<ProjectIrrigation> findAllIrrigationsByProjects(FarmProjects projects);

}
