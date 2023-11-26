package com.svastha.dto;

public class LocationDTO {

	private long timestamp;
	
	private String mocked;
	
	private Coords coords;
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMocked() {
		return mocked;
	}

	public void setMocked(String mocked) {
		this.mocked = mocked;
	}

	public Coords getCoords() {
		return coords;
	}

	public void setCoords(Coords coords) {
		this.coords = coords;
	}

	public class Coords
	{
		private String altitude;
		
		private String heading;
		
		private String altitudeAccuracy;
		
		private double latitude;
		
		private String speed;
		
		private double longitude;
		
		private String accuracy;

		public String getAltitude() {
			return altitude;
		}

		public void setAltitude(String altitude) {
			this.altitude = altitude;
		}

		public String getHeading() {
			return heading;
		}

		public void setHeading(String heading) {
			this.heading = heading;
		}

		public String getAltitudeAccuracy() {
			return altitudeAccuracy;
		}

		public void setAltitudeAccuracy(String altitudeAccuracy) {
			this.altitudeAccuracy = altitudeAccuracy;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public String getSpeed() {
			return speed;
		}

		public void setSpeed(String speed) {
			this.speed = speed;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		public String getAccuracy() {
			return accuracy;
		}

		public void setAccuracy(String accuracy) {
			this.accuracy = accuracy;
		}
	}
}
