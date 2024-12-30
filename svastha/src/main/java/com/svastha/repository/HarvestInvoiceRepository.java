package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestInvoice;

public interface HarvestInvoiceRepository extends JpaRepository<HarvestInvoice, Long> {

	// Returning data
	List<HarvestInvoice> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestInvoice> findByProjectsIn(List<FarmProjects> projects);
}
