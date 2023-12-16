package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectNurseryNutrient;

public interface ProjectsNutrientRepository extends JpaRepository<ProjectNurseryNutrient, Long> {

	List<ProjectNurseryNutrient> findAllNutrientByProjects(FarmProjects projects);

}
