package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FarmWorkers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private int ownWorkers;

	private int hiredWorkers;

	@ManyToOne
	private Farms farm;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public int getOwnWorkers() {
		return ownWorkers;
	}

	public void setOwnWorkers(int ownWorkers) {
		this.ownWorkers = ownWorkers;
	}

	public int getHiredWorkers() {
		return hiredWorkers;
	}

	public void setHiredWorkers(int hiredWorkers) {
		this.hiredWorkers = hiredWorkers;
	}

	public Farms getFarm() {
		return farm;
	}

	public void setFarm(Farms farm) {
		this.farm = farm;
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
}