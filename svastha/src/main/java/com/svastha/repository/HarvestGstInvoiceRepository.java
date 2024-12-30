package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestDryingProcess;
import com.svastha.entity.HarvestGstInvoice;

public interface HarvestGstInvoiceRepository extends JpaRepository<HarvestGstInvoice, Long> {

	// Returning data
	List<HarvestGstInvoice> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestGstInvoice> findByProjectsIn(List<FarmProjects> projects);
}
