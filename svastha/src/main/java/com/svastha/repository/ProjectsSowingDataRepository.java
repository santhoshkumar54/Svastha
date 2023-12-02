package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectSowingData;

public interface ProjectsSowingDataRepository extends JpaRepository<ProjectSowingData, Long> {

	List<ProjectSowingData> findAllSowingDataByProjects(FarmProjects projects);

}
