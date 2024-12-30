package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.HarvestInvoice;
import com.svastha.entity.HarvestInvoiceProducts;

public interface HarvestInvoiceProductsRepository extends JpaRepository<HarvestInvoiceProducts, Long> {

	// Returning data
	List<HarvestInvoiceProducts> findAllByInvoice(HarvestInvoice invoice);

}
