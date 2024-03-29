package com.svastha.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectSowingData;

public interface ProjectsSowingDataRepository extends JpaRepository<ProjectSowingData, Long> {

	List<ProjectSowingData> findAllByplots(FarmPlots plots, Sort sort);

	List<ProjectSowingData> findAllSowingDataByProjects(FarmProjects projects);

	List<ProjectSowingData> findByProjectsIn(List<FarmProjects> projects);

}
