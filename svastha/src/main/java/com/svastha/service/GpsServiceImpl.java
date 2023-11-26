package com.svastha.service;

import org.springframework.stereotype.Service;

@Service
public class GpsServiceImpl implements GpsService {

	static double SEMI_MAJOR_AXIS_MT = 6378137;
	static double SEMI_MINOR_AXIS_MT = 6356752.314245;
	static double FLATTENING = 1 / 298.257223563;
	static double ERROR_TOLERANCE = 1e-12;

	public long calculateBearing(double lat1, double lon1, double lat2, double lon2) {
		double deltaLon = lon2 - lon1;

		double x = Math.cos(Math.toRadians(lat2)) * Math.sin(Math.toRadians(deltaLon));
		double y = Math.cos(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
				- Math.sin(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(deltaLon));

		double bearing = Math.atan2(x, y);

		// Convert radians to degrees
		bearing = Math.toDegrees(bearing);

		// Normalize the bearing to be in the range [0, 360)
		bearing = (bearing + 360) % 360;

		return Math.round(bearing);
	}

	public long calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
		double U1 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(latitude1)));
		double U2 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(latitude2)));

		double sinU1 = Math.sin(U1);
		double cosU1 = Math.cos(U1);
		double sinU2 = Math.sin(U2);
		double cosU2 = Math.cos(U2);

		double longitudeDifference = Math.toRadians(longitude2 - longitude1);
		double previousLongitudeDifference;

		double sinSigma, cosSigma, sigma, sinAlpha, cosSqAlpha, cos2SigmaM;

		do {
			sinSigma = Math.sqrt(Math.pow(cosU2 * Math.sin(longitudeDifference), 2)
					+ Math.pow(cosU1 * sinU2 - sinU1 * cosU2 * Math.cos(longitudeDifference), 2));
			cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * Math.cos(longitudeDifference);
			sigma = Math.atan2(sinSigma, cosSigma);
			sinAlpha = cosU1 * cosU2 * Math.sin(longitudeDifference) / sinSigma;
			cosSqAlpha = 1 - Math.pow(sinAlpha, 2);
			cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
			if (Double.isNaN(cos2SigmaM)) {
				cos2SigmaM = 0;
			}
			previousLongitudeDifference = longitudeDifference;
			double C = FLATTENING / 16 * cosSqAlpha * (4 + FLATTENING * (4 - 3 * cosSqAlpha));
			longitudeDifference = Math.toRadians(longitude2 - longitude1) + (1 - C) * FLATTENING * sinAlpha
					* (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2))));
		} while (Math.abs(longitudeDifference - previousLongitudeDifference) > ERROR_TOLERANCE);

		double uSq = cosSqAlpha * (Math.pow(SEMI_MAJOR_AXIS_MT, 2) - Math.pow(SEMI_MINOR_AXIS_MT, 2))
				/ Math.pow(SEMI_MINOR_AXIS_MT, 2);

		double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
		double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));

		double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2))
				- B / 6 * cos2SigmaM * (-3 + 4 * Math.pow(sinSigma, 2)) * (-3 + 4 * Math.pow(cos2SigmaM, 2))));

		double distanceMt = SEMI_MINOR_AXIS_MT * A * (sigma - deltaSigma);
		return Math.round(distanceMt);
	}

	@Override
	public String calculateDirection(long bearing) {
		if( bearing >= 340 && bearing <= 20 )
		{
			return "N";
		}
		else if( bearing > 20 && bearing < 70 )
		{
			return "NE";
		}
		else if( bearing >= 70 && bearing <= 110 )
		{
			return "E";
		}
		else if( bearing > 110 && bearing < 160 )
		{
			return "SE";
		}
		else if( bearing >= 160 && bearing <= 200 )
		{
			return "S";
		}
		else if( bearing > 200 && bearing < 250 )
        {
	        return "SW";
        }
		else if( bearing >= 250 && bearing <= 290 )
		{
			return "W";
		}
		else if( bearing > 290 && bearing < 340 )
		{
			return "NW";
		}
		else
		{
			return "";
		}
	}

	@Override
	public String generateURL(double lat, double lon, String label) {
		String url = "https://www.google.com/maps/search/?api=1&query="+lat+","+lon+"&query_place_id="+label;
		return url;
	}
}
