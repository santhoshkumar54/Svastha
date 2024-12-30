package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestEwayBill;

public interface HarvestEwayBillRepository extends JpaRepository<HarvestEwayBill, Long> {

	// Returning data
	List<HarvestEwayBill> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestEwayBill> findByProjectsIn(List<FarmProjects> projects);
}
