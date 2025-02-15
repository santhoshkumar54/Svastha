package com.svastha.service;

import com.google.gson.Gson;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.RouteMaster;
import com.svastha.entity.Thaluk;
import com.svastha.entity.Village;
import com.svastha.logs.LogServiceFactory;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.MasterProjectTypeRepository;
import com.svastha.repository.RouteMasterRepository;
import com.svastha.repository.ThalukRepository;
import com.svastha.repository.VillageRepository;
import com.svastha.util.GeoMapDTO;
import com.svastha.util.LocationDTO;
import com.svastha.util.RouteLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

@Service
public class RouteService {

	private static final double MAX_DISTANCE = 50.0; // Maximum distance in kilometers

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private VillageRepository villageDao;

	@Autowired
	private ThalukRepository thalukDao;

	@Autowired
	private RouteMasterRepository routeMasterDao;

	@Autowired
	private MasterProjectTypeRepository projectTypeDao;

	public Page<RouteMaster> getRoutesWithFilters(String routeName, Integer count, Long assignedTo, Long assignedBy,
			Timestamp startDate, Timestamp endDate, Pageable pageable) {
		return routeMasterDao.findWithFilters(routeName, count, assignedTo, assignedBy, startDate, endDate, pageable);
	}

	public RouteMaster getRouteById(Long routeId) {
		Optional<RouteMaster> route = routeMasterDao.findById(routeId);
		return route.orElse(null);
	}

	public RouteMaster createRoute(RouteMaster route) {
		return routeMasterDao.save(route);
	}

	public void deleteRoute(RouteMaster route) {
		if (route != null && route.getPk1() != null) {
			resetProjectsInRoute(route);
			routeMasterDao.deleteById(route.getPk1());
		} else {
			throw new IllegalArgumentException("Route to delete is invalid");
		}
	}

	public void resetProjectsInRoute(RouteMaster route) {
		List<FarmProjects> projects = projectDao.findAllByRoutes(route);
		for (FarmProjects project : projects) {
			project.setRoutes(null);
			projectDao.save(project);
		}
	}

	public List<FarmProjects> getProjectsInRoute(Long routeId) {
		RouteMaster route = getRouteById(routeId);
		return projectDao.findAllByRoutes(route);
	}

	public Page<FarmProjects> getProjectsWithoutRoutes(Long yearId, Long seasonId, Long cropId, String key, Long userId,
			Long varietyId, Long ics, Long districtId, Long thalukId, Long villageId, Pageable pageable) {
		Long projectTypePk1 = projectTypeDao.findByProjectType("MRL").getPk1();
		Page<FarmProjects> projects = projectDao.findWithFiltersWithoutRoute(yearId, seasonId, cropId, key, userId,
				projectTypePk1, varietyId, ics, "APPROVED", districtId, thalukId, villageId, pageable);
		return projects;
	}

	public List<FarmProjects> saveProjectInRoutes(RouteMaster route, List<FarmProjects> projects) {
		List<FarmProjects> projectsinRoute = projectDao.findAllByRoutes(route);
		for (FarmProjects project : projectsinRoute) {
			project.setRoutes(null);
			projectDao.save(project);
		}
		for (FarmProjects project : projects) {
			project.setRoutes(route);
			projectDao.save(project);
		}
		route.setCount(projects.size());
		routeMasterDao.save(route);
		return projectDao.findAllByRoutes(route);
	}

	@Async
	public void villageLocationMapper(Long thalukId) {
		String apiKey = "65e4896cbe6a7019036672grs89364e";

		Thaluk t = thalukDao.findById(thalukId).get();
		List<Village> villages = villageDao.findByThaluk(t);

		for (Village village : villages) {
			if (!village.getName().equals("Others") && village.getLatitude() == null) {
				try {
					Thread.sleep(1000);
					System.out.println("Fetching weather for" + village.getName());
					String apiURL = "https://geocode.maps.co/search";
					apiURL = UriComponentsBuilder.fromHttpUrl(apiURL).queryParam("q", village.getName())
							.queryParam("api_key", apiKey).build().toUriString();
					System.out.println(apiURL);

					RestTemplate restTemplate = new RestTemplate();
					GeoMapDTO[] w = restTemplate.getForObject(apiURL, GeoMapDTO[].class);
					StringBuilder db = new StringBuilder();
					db.append("Result: ");
					for (GeoMapDTO geo : w) {
						db.append(geo.getDisplayName());
						if (geo.getDisplayName().contains(village.getThaluk().getName())) {
							village.setLatitude(Double.parseDouble(geo.getLat()));
							village.setLongitude(Double.parseDouble(geo.getLon()));
							villageDao.save(village);
							break;
						}
					}
					System.out.println(db.toString());
				}

				catch (Exception ex) {
					System.out.println("error in fetching geo location " + ex);
				}
			}
		}

		List<Thaluk> thaluks = thalukDao.findAll();
		for (Thaluk thaluk : thaluks) {
			if (!thaluk.getName().equals("Others") && thaluk.getLatitude() == null) {
				try {
					Thread.sleep(2000);
					System.out.println("Fetching weather for" + thaluk.getName());
					String apiURL = "https://geocode.maps.co/search";
					apiURL = UriComponentsBuilder.fromHttpUrl(apiURL).queryParam("q", thaluk.getName())
							.queryParam("api_key", apiKey).build().toUriString();
					System.out.println(apiURL);

					RestTemplate restTemplate = new RestTemplate();
					GeoMapDTO[] w = restTemplate.getForObject(apiURL, GeoMapDTO[].class);
					for (GeoMapDTO geo : w) {
						if (geo.getDisplayName().contains(thaluk.getDistrict().getName())) {
							thaluk.setLatitude(Double.parseDouble(geo.getLat()));
							thaluk.setLongitude(Double.parseDouble(geo.getLon()));
							thalukDao.save(thaluk);
							break;
						}
					}

				}

				catch (Exception ex) {
					System.out.println("error in fetching geo location " + ex);
				}
			}
		}
	}

	public void oneTimeRouteCreation() {
		List<FarmProjects> projects = projectDao.findWithLocations();
		System.out.println(projects.size());
		LogServiceFactory.getService().logError("Teting error message", null);
		LogServiceFactory.getService().logInfo("testing ingo");
		List<RouteLocation> locations = new ArrayList<>();
		for (FarmProjects project : projects) {
			LocationDTO loc = new Gson().fromJson(project.getFarm().getLocation(), LocationDTO.class);
			if (project.getFarm().getVillageId() != null && project.getFarm().getThalukId() != null
					&& project.getFarm().getDistrictId() != null)

			{
				RouteLocation location = new RouteLocation(loc.getCoords().getLatitude(),
						loc.getCoords().getLongitude(), project);
				locations.add(location);
			}
		}

		List<List<RouteLocation>> routes = planRoutes(locations);

		for (int i = 0; i < routes.size(); i++) {
			try {
				for (RouteLocation loc : routes.get(i)) {
					System.out.println("Route " + (i + 1) + "|" + loc.getLatitude() + "|" + loc.getLongitude() + "|"
							+ loc.getProjects().getFarm().getVillageId().getName() + "|"
							+ loc.getProjects().getFarm().getVillageId().getThaluk().getName() + "|"
							+ loc.getProjects().getFarm().getVillageId().getThaluk().getDistrict().getName() + "|"
							+ routes.get(i).size());
				}
			} catch (Exception e) {
				LogServiceFactory.getService().logError("Teting error messafdfdfge", e);
			}
		}
	}

	public List<List<RouteLocation>> planRoutes(List<RouteLocation> locations) {
		try {
			List<List<RouteLocation>> routes = new ArrayList<>();
			Set<RouteLocation> remainingLocations = new HashSet<>(locations);

			// Sort initial locations by distance from the static location
			List<RouteLocation> sortedLocations = new ArrayList<>(remainingLocations);
			sortedLocations.sort(Comparator
					.comparingDouble(loc -> haversine(11.102534, 79.643582, loc.getLatitude(), loc.getLongitude())));
			remainingLocations.clear();
			remainingLocations.addAll(sortedLocations);

			for (int i = 0; i < sortedLocations.size(); i++) {
				RouteLocation start = sortedLocations.get(i);

				System.out.println(start.getLatitude() + "|" + start.getLongitude() + "|"
						+ start.getProjects().getFarm().getVillageId().getName() + "|"
						+ start.getProjects().getFarm().getVillageId().getThaluk().getName() + "|"
						+ start.getProjects().getFarm().getVillageId().getThaluk().getDistrict().getName());

			}
			while (!remainingLocations.isEmpty()) {
				RouteLocation start = remainingLocations.iterator().next();

				List<RouteLocation> route = new ArrayList<>();
				route.add(start);
				remainingLocations.remove(start);
				List<RouteLocation> nearestLocations = findNearestLocations(start, remainingLocations,
						MAX_LOCATIONS_PER_ROUTE - 1);
				route.addAll(nearestLocations);
				remainingLocations.removeAll(nearestLocations);

				routes.add(route);
			}
			return routes;
		} catch (Exception ex) {
			LogServiceFactory.getService().logError("Teting error messag1e", ex);
			return null;
		}
	}

	private List<RouteLocation> findNearestLocations(RouteLocation start, Set<RouteLocation> locations, int count) {

		PriorityQueue<LocationDistance> pq = new PriorityQueue<>(
				Comparator.comparingDouble(LocationDistance::getDistance));

		for (RouteLocation loc : locations) {
			double distance = haversine(start.getLatitude(), start.getLongitude(), loc.getLatitude(),
					loc.getLongitude());
			if (distance <= MAX_DISTANCE) {
				pq.add(new LocationDistance(loc, distance));
			}
		}

		List<RouteLocation> nearestLocations = new ArrayList<>();
		while (nearestLocations.size() < count && !pq.isEmpty()) {
			nearestLocations.add(pq.poll().getLocation());
		}

		return nearestLocations;
	}

	private static final double EARTH_RADIUS = 6371.0; // Radius in kilometers

	private static final int MAX_LOCATIONS_PER_ROUTE = 15;

	public static double haversine(double lat1, double lon1, double lat2, double lon2) {
		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return EARTH_RADIUS * c;
	}

	private static class LocationDistance {
		private final RouteLocation location;
		private final double distance;

		public LocationDistance(RouteLocation location, double distance) {
			this.location = location;
			this.distance = distance;
		}

		public RouteLocation getLocation() {
			return location;
		}

		public double getDistance() {
			return distance;
		}
	}
}
