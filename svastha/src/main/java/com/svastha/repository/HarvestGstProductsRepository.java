package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.HarvestGstInvoice;
import com.svastha.entity.HarvestGstProducts;

public interface HarvestGstProductsRepository extends JpaRepository<HarvestGstProducts, Long> {

	// Returning data
	List<HarvestGstProducts> findAllByInvoice(HarvestGstInvoice invoice);

}
