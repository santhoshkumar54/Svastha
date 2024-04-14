package com.svastha.model;

import java.io.Serializable;
import java.util.List;

import com.svastha.entity.MasterCrop;
import com.svastha.entity.MasterProjectType;
import com.svastha.entity.MasterSeason;
import com.svastha.entity.MasterYear;

public class MasterProjectModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private List<MasterYear> year;

	private List<MasterSeason> season;

	private List<MasterCrop> crop;

	private List<MasterProjectType> projectType;

	public List<MasterProjectType> getProjectType() {
		return projectType;
	}

	public void setProjectType(List<MasterProjectType> projectType) {
		this.projectType = projectType;
	}

	public List<MasterYear> getYear() {
		return year;
	}

	public void setYear(List<MasterYear> year) {
		this.year = year;
	}

	public List<MasterSeason> getSeason() {
		return season;
	}

	public void setSeason(List<MasterSeason> season) {
		this.season = season;
	}

	public List<MasterCrop> getCrop() {
		return crop;
	}

	public void setCrop(List<MasterCrop> crop) {
		this.crop = crop;
	}

}
