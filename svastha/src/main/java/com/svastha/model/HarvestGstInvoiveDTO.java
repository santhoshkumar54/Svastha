package com.svastha.model;

import com.svastha.entity.HarvestGstInvoice;
import com.svastha.entity.HarvestGstProducts;

public class HarvestGstInvoiveDTO {

	private HarvestGstInvoice gst;

	private Iterable<HarvestGstProducts> products;

	public HarvestGstInvoice getGst() {
		return gst;
	}

	public void setGst(HarvestGstInvoice gst) {
		this.gst = gst;
	}

	public Iterable<HarvestGstProducts> getProducts() {
		return products;
	}

	public void setProducts(Iterable<HarvestGstProducts> products) {
		this.products = products;
	}

}
