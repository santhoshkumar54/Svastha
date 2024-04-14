package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicAnnualProgram;

public interface OrganicAnnualProgramRepository extends JpaRepository<OrganicAnnualProgram, Long> {

	OrganicAnnualProgram findByProjectsAndPlots(FarmProjects projects,FarmPlots plots);

	List<OrganicAnnualProgram> findByProjectsIn(List<FarmProjects> projects);
	
	List<OrganicAnnualProgram> findByProjects(FarmProjects projects);

}
