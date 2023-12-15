package com.svastha.model;

import java.io.Serializable;
import java.util.List;

import com.svastha.entity.MasterPests;

public class DiseaseAndPestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private MasterPests pests;

	private List<ChemicalBrandModel> chemicals;

	public MasterPests getPests() {
		return pests;
	}

	public void setPests(MasterPests pests) {
		this.pests = pests;
	}

	public List<ChemicalBrandModel> getChemicals() {
		return chemicals;
	}

	public void setChemicals(List<ChemicalBrandModel> chemicals) {
		this.chemicals = chemicals;
	}
}