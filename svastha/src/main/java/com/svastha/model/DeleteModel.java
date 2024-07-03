package com.svastha.model;

import java.io.Serializable;

import com.svastha.entity.Farms;
import com.svastha.entity.Users;

public class DeleteModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private Farms farm;
	private Users user;

	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Farms getFarm() {
		return farm;
	}

	public void setFarm(Farms farm) {
		this.farm = farm;
	}


}
