package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestDryingProcess;
import com.svastha.entity.HarvestPaymentDetails;

public interface HarvestPaymentDetailsRepository extends JpaRepository<HarvestPaymentDetails, Long> {

	// Returning data
	List<HarvestPaymentDetails> findAllByProjects(FarmProjects projects);

	// THis will be used for excel export purpose.
	List<HarvestPaymentDetails> findByProjectsIn(List<FarmProjects> projects);
}
