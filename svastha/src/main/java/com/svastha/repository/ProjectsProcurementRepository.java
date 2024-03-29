package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectProcurement;

public interface ProjectsProcurementRepository extends JpaRepository<ProjectProcurement, Long> {

	List<ProjectProcurement> findAllByProjects(FarmProjects projects);

	List<ProjectProcurement> findByProjectsIn(List<FarmProjects> projects);
}
