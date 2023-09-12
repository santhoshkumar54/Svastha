package com.svastha.model;

import java.io.Serializable;
import java.util.List;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.LandPreparation;
import com.svastha.entity.NurseryManagement;
import com.svastha.entity.TransplantManagement;

public class ProjectModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private FarmProjects farm;
	
	private List<LandPreparation> landPreparation;
	
	private List<NurseryManagement> nursery;
	
	private List<TransplantManagement> transplant;

	public FarmProjects getFarm() {
		return farm;
	}

	public void setFarm(FarmProjects farm) {
		this.farm = farm;
	}

	public List<LandPreparation> getLandPreparation() {
		return landPreparation;
	}

	public void setLandPreparation(List<LandPreparation> landPreparation) {
		this.landPreparation = landPreparation;
	}

	public List<NurseryManagement> getNursery() {
		return nursery;
	}

	public void setNursery(List<NurseryManagement> nursery) {
		this.nursery = nursery;
	}

	public List<TransplantManagement> getTransplant() {
		return transplant;
	}

	public void setTransplant(List<TransplantManagement> transplant) {
		this.transplant = transplant;
	}
}
