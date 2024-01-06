package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectDispatch;

public interface ProjectsDispatchRepository extends JpaRepository<ProjectDispatch, Long> {

	List<ProjectDispatch> findAllByProjects(FarmProjects projects);

}
