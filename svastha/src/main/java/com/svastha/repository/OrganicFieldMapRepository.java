package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicFieldMap;

public interface OrganicFieldMapRepository extends JpaRepository<OrganicFieldMap, Long> {

	OrganicFieldMap findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicFieldMap> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicFieldMap> findByProjects(FarmProjects projects);

}
