package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectMicroNutrient;

public interface ProjectsMicroNutrientRepository extends JpaRepository<ProjectMicroNutrient, Long> {

	List<ProjectMicroNutrient> findAllByProjects(FarmProjects projects);

	List<ProjectMicroNutrient> findByProjectsIn(List<FarmProjects> projects);

}
