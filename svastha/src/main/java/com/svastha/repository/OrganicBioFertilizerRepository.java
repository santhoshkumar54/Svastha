package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicBioFertilizer;

public interface OrganicBioFertilizerRepository extends JpaRepository<OrganicBioFertilizer, Long> {

	List<OrganicBioFertilizer> findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicBioFertilizer> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicBioFertilizer> findByProjects(FarmProjects projects);

}
