package com.svastha.model;

import java.io.Serializable;
import java.util.List;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectLandPreparation;
import com.svastha.entity.NurseryManagement;
import com.svastha.entity.ProjectTransplantManagement;

public class ProjectModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private FarmProjects farm;
	
	private List<ProjectLandPreparation> landPreparation;
	
	private List<NurseryManagement> nursery;
	
	private List<ProjectTransplantManagement> transplant;

	public FarmProjects getFarm() {
		return farm;
	}

	public void setFarm(FarmProjects farm) {
		this.farm = farm;
	}

	public List<ProjectLandPreparation> getLandPreparation() {
		return landPreparation;
	}

	public void setLandPreparation(List<ProjectLandPreparation> landPreparation) {
		this.landPreparation = landPreparation;
	}

	public List<NurseryManagement> getNursery() {
		return nursery;
	}

	public void setNursery(List<NurseryManagement> nursery) {
		this.nursery = nursery;
	}

	public List<ProjectTransplantManagement> getTransplant() {
		return transplant;
	}

	public void setTransplant(List<ProjectTransplantManagement> transplant) {
		this.transplant = transplant;
	}
}
