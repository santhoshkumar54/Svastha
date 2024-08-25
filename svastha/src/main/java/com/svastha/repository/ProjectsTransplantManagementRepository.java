package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectTransplantManagement;

public interface ProjectsTransplantManagementRepository extends JpaRepository<ProjectTransplantManagement, Long> {

	List<ProjectTransplantManagement> findAllByProject(FarmProjects project);

	List<ProjectTransplantManagement> findAllByProjectAndPlots(FarmProjects project, FarmPlots plots);

	List<ProjectTransplantManagement> findByProjectIn(List<FarmProjects> projects);
}
