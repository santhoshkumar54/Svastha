package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectLandPreparation;

public interface ProjectsLandPreparationRepository extends JpaRepository<ProjectLandPreparation, Long> {

	List<ProjectLandPreparation> findAllByProject(FarmProjects project);

	List<ProjectLandPreparation> findByProjectIn(List<FarmProjects> projects);

}
