package com.svastha.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Manual {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pk1;

	private String entry;

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	
}