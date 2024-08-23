package com.svastha.model;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectBioFertilizers;
import com.svastha.entity.ProjectSyntheticFertilizers;

public class ProjectExportModel {

	private FarmProjects projects;

	private ProjectBioFertilizers bioFertilizers;
	
	private ProjectSyntheticFertilizers syntheticFertilizers;

	public FarmProjects getProjects() {
		return projects;
	}

	public void setProjects(FarmProjects projects) {
		this.projects = projects;
	}

	public ProjectBioFertilizers getBioFertilizers() {
		return bioFertilizers;
	}

	public void setBioFertilizers(ProjectBioFertilizers bioFertilizers) {
		this.bioFertilizers = bioFertilizers;
	}

	public ProjectSyntheticFertilizers getSyntheticFertilizers() {
		return syntheticFertilizers;
	}

	public void setSyntheticFertilizers(ProjectSyntheticFertilizers syntheticFertilizers) {
		this.syntheticFertilizers = syntheticFertilizers;
	}
}
