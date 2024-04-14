package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class viewNotification {

	@Id
	private Long pk1;

	private Long userId;

	private String type;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}