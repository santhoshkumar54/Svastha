package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectNurseryNutrient;

public interface ProjectsNutrientRepository extends JpaRepository<ProjectNurseryNutrient, Long> {

	List<ProjectNurseryNutrient> findAllNutrientByProjects(FarmProjects projects);

	List<ProjectNurseryNutrient> findAllNutrientByProjectsAndPlot(FarmProjects projects,FarmPlots plot);

	List<ProjectNurseryNutrient> findByProjectsIn(List<FarmProjects> projects);
}
