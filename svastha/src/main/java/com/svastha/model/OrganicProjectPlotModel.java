package com.svastha.model;

import java.io.Serializable;
import java.util.List;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicAnnualProgram;
import com.svastha.entity.OrganicCropVariety;
import com.svastha.entity.OrganicFieldMap;
import com.svastha.entity.OrganicPlotBoundary;

public class OrganicProjectPlotModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private FarmProjects farm;

	private OrganicAnnualProgram annualProgram;

	private List<OrganicPlotBoundary> boundary;

	private OrganicCropVariety cropVariety;

	private OrganicFieldMap fieldMap;

	public FarmProjects getFarm() {
		return farm;
	}

	public void setFarm(FarmProjects farm) {
		this.farm = farm;
	}

	public OrganicAnnualProgram getAnnualProgram() {
		return annualProgram;
	}

	public void setAnnualProgram(OrganicAnnualProgram annualProgram) {
		this.annualProgram = annualProgram;
	}

	public List<OrganicPlotBoundary> getBoundary() {
		return boundary;
	}

	public void setBoundary(List<OrganicPlotBoundary> boundary) {
		this.boundary = boundary;
	}

	public OrganicCropVariety getCropVariety() {
		return cropVariety;
	}

	public void setCropVariety(OrganicCropVariety cropVariety) {
		this.cropVariety = cropVariety;
	}

	public OrganicFieldMap getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(OrganicFieldMap fieldMap) {
		this.fieldMap = fieldMap;
	}
}
