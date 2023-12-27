package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MasterCropVariety {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pk1;

	private String variety;

	@ManyToOne
	private MasterSeason season;

	public int getPk1() {
		return pk1;
	}

	public void setPk1(int pk1) {
		this.pk1 = pk1;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public MasterSeason getSeason() {
		return season;
	}

	public void setSeason(MasterSeason season) {
		this.season = season;
	}

	

}
