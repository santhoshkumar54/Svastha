package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectWeedManagement;

public interface ProjectsWeedManagementRepository extends JpaRepository<ProjectWeedManagement, Long> {

	List<ProjectWeedManagement> findAllByProjects(FarmProjects projects);

	List<ProjectWeedManagement> findByProjectsIn(List<FarmProjects> projects);
}
