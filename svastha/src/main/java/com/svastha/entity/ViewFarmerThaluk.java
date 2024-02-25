package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ViewFarmerThaluk {

	@Id
	private String thaluk;

	private String count;

	public String getThaluk() {
		return thaluk;
	}

	public void setThaluk(String thaluk) {
		this.thaluk = thaluk;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}