package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestSampleTest;

public interface HarvestSampleTestRepository extends JpaRepository<HarvestSampleTest, Long> {

	// Returning data
	List<HarvestSampleTest> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestSampleTest> findByProjectsIn(List<FarmProjects> projects);
}
