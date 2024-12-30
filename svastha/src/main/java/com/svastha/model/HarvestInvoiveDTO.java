package com.svastha.model;

import com.svastha.entity.HarvestInvoice;
import com.svastha.entity.HarvestInvoiceProducts;

public class HarvestInvoiveDTO {

	private HarvestInvoice invoice;

	private Iterable<HarvestInvoiceProducts> products;

	public HarvestInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(HarvestInvoice invoice) {
		this.invoice = invoice;
	}

	public Iterable<HarvestInvoiceProducts> getProducts() {
		return products;
	}

	public void setProducts(Iterable<HarvestInvoiceProducts> products) {
		this.products = products;
	}
}
