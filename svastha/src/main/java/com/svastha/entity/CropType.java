package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CropType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pk1;

	private String type_name;

	private int crop;

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public int getCrop() {
		return crop;
	}

	public void setCrop(int crop) {
		this.crop = crop;
	}

}
