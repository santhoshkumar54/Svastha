package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectNurseryWeed;

public interface ProjectsWeedRepository extends JpaRepository<ProjectNurseryWeed, Long> {

	List<ProjectNurseryWeed> findAllWeedByProjects(FarmProjects projects);

	List<ProjectNurseryWeed> findByProjectsIn(List<FarmProjects> projects);
}
