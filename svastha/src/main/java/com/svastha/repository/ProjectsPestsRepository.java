package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectNurseryPests;

public interface ProjectsPestsRepository extends JpaRepository<ProjectNurseryPests, Long> {

	List<ProjectNurseryPests> findAllPestsByProjects(FarmProjects projects);

	List<ProjectNurseryPests> findAllPestsByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<ProjectNurseryPests> findByProjectsIn(List<FarmProjects> projects);
}
