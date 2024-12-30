package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestGstInvoice;

public interface HarvestGstInvoiceRepository extends JpaRepository<HarvestGstInvoice, Long> {

	// Returning data
	HarvestGstInvoice findByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestGstInvoice> findByProjectsIn(List<FarmProjects> projects);
}
