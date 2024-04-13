package com.svastha.model;

import java.io.Serializable;
import java.util.List;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicAnnualProgram;
import com.svastha.entity.OrganicCropVariety;
import com.svastha.entity.OrganicFieldMap;
import com.svastha.entity.OrganicPlotBoundary;

public class OrganicProjectModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private FarmProjects farm;

	private List<OrganicAnnualProgram> annualProgram;

	private List<OrganicPlotBoundary> boundary;

	private List<OrganicCropVariety> cropVariety;

	private List<OrganicFieldMap> fieldMap;

	public FarmProjects getFarm() {
		return farm;
	}

	public void setFarm(FarmProjects farm) {
		this.farm = farm;
	}

	public List<OrganicAnnualProgram> getAnnualProgram() {
		return annualProgram;
	}

	public void setAnnualProgram(List<OrganicAnnualProgram> annualProgram) {
		this.annualProgram = annualProgram;
	}

	public List<OrganicPlotBoundary> getBoundary() {
		return boundary;
	}

	public void setBoundary(List<OrganicPlotBoundary> boundary) {
		this.boundary = boundary;
	}

	public List<OrganicCropVariety> getCropVariety() {
		return cropVariety;
	}

	public void setCropVariety(List<OrganicCropVariety> cropVariety) {
		this.cropVariety = cropVariety;
	}

	public List<OrganicFieldMap> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(List<OrganicFieldMap> fieldMap) {
		this.fieldMap = fieldMap;
	}
}
