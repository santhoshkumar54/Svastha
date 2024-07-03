package com.svastha.model;

import java.io.Serializable;
import java.util.List;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.Users;

public class AssignmentModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private List<FarmProjects> projects;
	private Users user;
	private Users updatedUser;

	public List<FarmProjects> getProjects() {
		return projects;
	}

	public void setProjects(List<FarmProjects> projects) {
		this.projects = projects;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Users getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(Users updatedUser) {
		this.updatedUser = updatedUser;
	}

}
