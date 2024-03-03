package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class viewFarmerCreatedweek {

	@Id
	private String week;
	private String count;

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}