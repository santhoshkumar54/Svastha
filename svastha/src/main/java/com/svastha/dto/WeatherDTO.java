package com.svastha.dto;


public class WeatherDTO {
	private Timelines timelines;

	public Timelines getTimelines() {
		return timelines;
	}

	public void setTimelines(Timelines timelines) {
		this.timelines = timelines;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	private Location location;
}


