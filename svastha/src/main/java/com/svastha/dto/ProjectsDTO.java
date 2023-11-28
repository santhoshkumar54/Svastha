package com.svastha.dto;

import com.svastha.entity.FarmProjects;

public class ProjectsDTO {

	private FarmProjects projects;

	private Iterable<PlotsDTO> plots;

	public Iterable<PlotsDTO> getPlots() {
		return plots;
	}

	public void setPlots(Iterable<PlotsDTO> plots) {
		this.plots = plots;
	}

	public FarmProjects getProjects() {
		return projects;
	}
 
	public void setProjects(FarmProjects projects) {
		this.projects = projects;
	}

}
