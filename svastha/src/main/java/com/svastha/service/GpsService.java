package com.svastha.service;

public interface GpsService {

	public long calculateBearing(double lat1, double lon1, double lat2, double lon2);

	public long calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2);

	public String calculateDirection( long bearing);
	
	public String generateURL(double lat, double lon, String label);
}
