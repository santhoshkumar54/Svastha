package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicProtection;

public interface OrganicProtectionRepository extends JpaRepository<OrganicProtection, Long> {

	List<OrganicProtection> findByProjectsAndPlots(FarmProjects projects,FarmPlots plots);

	List<OrganicProtection> findByProjectsIn(List<FarmProjects> projects);
	
	List<OrganicProtection> findByProjects(FarmProjects projects);

}
