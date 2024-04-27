package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicOrganicManure;

public interface OrganicOrganicManureRepository extends JpaRepository<OrganicOrganicManure, Long> {

	List<OrganicOrganicManure> findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicOrganicManure> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicOrganicManure> findByProjects(FarmProjects projects);

}
