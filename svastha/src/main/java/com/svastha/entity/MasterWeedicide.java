package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MasterWeedicide {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String name;

	private String brand;

	private String dose;

	private String time;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Users getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Users createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public Users getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Users updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Timestamp updatedDt) {
		this.updatedDt = updatedDt;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
