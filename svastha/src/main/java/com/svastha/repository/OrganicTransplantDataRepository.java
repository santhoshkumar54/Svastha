package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicTransplantData;

public interface OrganicTransplantDataRepository extends JpaRepository<OrganicTransplantData, Long> {

	OrganicTransplantData findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicTransplantData> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicTransplantData> findByProjects(FarmProjects projects);

}
