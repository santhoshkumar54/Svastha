package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectProtection;

public interface ProjectsProtectionRepository extends JpaRepository<ProjectProtection, Long> {

	List<ProjectProtection> findAllProtectionsByProjects(FarmProjects projects);

	List<ProjectProtection> findAllProtectionsByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<ProjectProtection> findByProjectsIn(List<FarmProjects> projects);
}
