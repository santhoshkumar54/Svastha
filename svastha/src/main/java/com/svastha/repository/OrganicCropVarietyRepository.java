package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicCropVariety;

public interface OrganicCropVarietyRepository extends JpaRepository<OrganicCropVariety, Long> {

    OrganicCropVariety findByProjectsAndPlots(FarmProjects projects,FarmPlots plots);

	List<OrganicCropVariety> findByProjectsIn(List<FarmProjects> projects);
	
	List<OrganicCropVariety> findByProjects(FarmProjects projects);

}
