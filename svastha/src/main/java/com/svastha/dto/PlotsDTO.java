package com.svastha.dto;

public class PlotsDTO {

	private long id;

	private String number;

	private String location;

	private double lat;

	private double lon;

	private long distance;

	private long bearing;

	private String urlName;

	private String url;

	private String cropStage;

	private String sowingDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public long getBearing() {
		return bearing;
	}

	public void setBearing(long bearing) {
		this.bearing = bearing;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCropStage() {
		return cropStage;
	}

	public void setCropStage(String cropStage) {
		this.cropStage = cropStage;
	}

	public String getSowingDate() {
		return sowingDate;
	}

	public void setSowingDate(String sowingDate) {
		this.sowingDate = sowingDate;
	}
}
