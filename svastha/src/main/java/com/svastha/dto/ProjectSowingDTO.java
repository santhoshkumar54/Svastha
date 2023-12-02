package com.svastha.dto;

import com.svastha.entity.ProjectSowingData;
import com.svastha.entity.ProjectSowingPlots;

public class ProjectSowingDTO {

	private ProjectSowingData sowingData;

	private Iterable<ProjectSowingPlots> plots;

	public ProjectSowingData getSowingData() {
		return sowingData;
	}

	public void setSowingData(ProjectSowingData sowingData) {
		this.sowingData = sowingData;
	}

	public Iterable<ProjectSowingPlots> getPlots() {
		return plots;
	}

	public void setPlots(Iterable<ProjectSowingPlots> plots) {
		this.plots = plots;
	}

}
