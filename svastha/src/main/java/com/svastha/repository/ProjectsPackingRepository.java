package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectPacking;

public interface ProjectsPackingRepository extends JpaRepository<ProjectPacking, Long> {

	List<ProjectPacking> findAllByProjects(FarmProjects projects);

}
