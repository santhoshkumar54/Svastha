package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestInvoice;

public interface HarvestInvoiceRepository extends JpaRepository<HarvestInvoice, Long> {

	// Returning data
	HarvestInvoice findByProjects(FarmProjects projects);

}
