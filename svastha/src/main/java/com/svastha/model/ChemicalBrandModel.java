package com.svastha.model;

import java.io.Serializable;
import java.util.List;

import com.svastha.entity.MasterChemicalBrands;
import com.svastha.entity.MasterChemicals;

public class ChemicalBrandModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private MasterChemicals chemicals;

	private List<MasterChemicalBrands> brands;

	public MasterChemicals getChemicals() {
		return chemicals;
	}

	public void setChemicals(MasterChemicals chemicals) {
		this.chemicals = chemicals;
	}

	public List<MasterChemicalBrands> getBrands() {
		return brands;
	}

	public void setBrands(List<MasterChemicalBrands> brands) {
		this.brands = brands;
	}
}
