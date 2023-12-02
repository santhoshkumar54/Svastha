package com.svastha.dto;

import com.svastha.entity.ProjectSeedTreatment;
import com.svastha.entity.ProjectSeedTreatmentChemical;

public class ProjectSeedTreatmentDTO {

	private ProjectSeedTreatment seedTreatment;

	private Iterable<ProjectSeedTreatmentChemical> chemicals;

	public ProjectSeedTreatment getSeedTreatment() {
		return seedTreatment;
	}

	public void setSeedTreatment(ProjectSeedTreatment seedTreatment) {
		this.seedTreatment = seedTreatment;
	}

	public Iterable<ProjectSeedTreatmentChemical> getChemicals() {
		return chemicals;
	}

	public void setChemicals(Iterable<ProjectSeedTreatmentChemical> chemicals) {
		this.chemicals = chemicals;
	}

	

}
