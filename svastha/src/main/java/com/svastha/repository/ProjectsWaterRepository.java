package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectNurseryWater;

public interface ProjectsWaterRepository extends JpaRepository<ProjectNurseryWater, Long> {

	List<ProjectNurseryWater> findAllWaterByProjects(FarmProjects projects);

}
