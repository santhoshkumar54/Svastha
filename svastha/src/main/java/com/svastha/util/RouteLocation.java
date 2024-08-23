package com.svastha.util;

import com.svastha.entity.FarmProjects;

public class RouteLocation {

	public RouteLocation(Double lat, Double lon) {
		this.latitude = lat;
		this.longitude = lon;
	}

	public RouteLocation(Double lat, Double lon,FarmProjects projects) {
		this.latitude = lat;
		this.longitude = lon;
		this.projects = projects;
	}
	
	public RouteLocation()
	{
		
	}
	
	private double latitude;

	private double longitude;

	private FarmProjects projects;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public FarmProjects getProjects() {
		return projects;
	}

	public void setProjects(FarmProjects projects) {
		this.projects = projects;
	}

}
