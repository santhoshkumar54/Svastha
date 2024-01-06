package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectStorage;

public interface ProjectsStorageRepository extends JpaRepository<ProjectStorage, Long> {

	List<ProjectStorage> findAllByProjects(FarmProjects projects);

}
