package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.ProjectSowingData;
import com.svastha.entity.ProjectSowingPlots;

public interface ProjectsSowingPlotsRepository extends JpaRepository<ProjectSowingPlots, Long> {

	List<ProjectSowingPlots> findAllPlotsBySowing(ProjectSowingData sowingData);

}
