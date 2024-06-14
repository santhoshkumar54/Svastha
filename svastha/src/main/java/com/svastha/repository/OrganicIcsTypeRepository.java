package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicIcsType;

public interface OrganicIcsTypeRepository extends JpaRepository<OrganicIcsType, Long> {

	OrganicIcsType findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicIcsType> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicIcsType> findByProjects(FarmProjects projects);

}
