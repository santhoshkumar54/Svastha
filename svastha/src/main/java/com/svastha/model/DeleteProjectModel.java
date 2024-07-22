package com.svastha.model;

import java.io.Serializable;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.Users;

public class DeleteProjectModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private FarmProjects project;
	private Users user;

	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public FarmProjects getProject() {
		return project;
	}

	public void setProject(FarmProjects project) {
		this.project = project;
	}
}
