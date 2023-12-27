package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectOrganicManure;

public interface ProjectsManureRepository extends JpaRepository<ProjectOrganicManure, Long> {

	List<ProjectOrganicManure> findAllByProjects(FarmProjects projects);

}
