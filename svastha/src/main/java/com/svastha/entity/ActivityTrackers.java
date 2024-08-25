package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ActivityTrackers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;
	
	private String activityType;
	
	private String activitySection;
	
	private String activityForm;
	
	private Long primaryKey;

	private int count;
	
	@ManyToOne
	private Users user;

	private Timestamp activityDate;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivitySection() {
		return activitySection;
	}

	public void setActivitySection(String activitySection) {
		this.activitySection = activitySection;
	}

	public String getActivityForm() {
		return activityForm;
	}

	public void setActivityForm(String activityForm) {
		this.activityForm = activityForm;
	}

	public Long getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Timestamp getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Timestamp activityDate) {
		this.activityDate = activityDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
