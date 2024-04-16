package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class viewNotification {

	private Long count;

	private String message;

	@Id
	private String type;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}