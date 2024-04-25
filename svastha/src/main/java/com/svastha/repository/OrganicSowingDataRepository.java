package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicSowingData;

public interface OrganicSowingDataRepository extends JpaRepository<OrganicSowingData, Long> {

	OrganicSowingData findByProjectsAndPlots(FarmProjects projects, FarmPlots plots);

	List<OrganicSowingData> findByProjectsIn(List<FarmProjects> projects);

	List<OrganicSowingData> findByProjects(FarmProjects projects);

}
