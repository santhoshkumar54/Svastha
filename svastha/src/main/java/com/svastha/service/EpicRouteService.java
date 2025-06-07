package com.svastha.service;

import com.google.gson.Gson;
import com.svastha.entity.*;
import com.svastha.repository.*;
import com.svastha.util.LocationDTO;
import com.svastha.util.RouteLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EpicRouteService {

    @Autowired
    private EpicRouteRepository epicRouteRepository;

    @Autowired
    private FarmProjectRepository farmProjectRepository;

    private static final double MAX_DISTANCE_KM = 50.0;

    public List<EpicRoute> getAllRoutes() {
        return epicRouteRepository.findAll();
    }

    public Optional<EpicRoute> getRouteById(Long id) {
        return epicRouteRepository.findById(id);
    }

    public List<EpicRoute> getRoutesByEpic(Long epicId) {
        return epicRouteRepository.findByEpicPk1(epicId);
    }

    public List<EpicRoute> generateRoutesForEpic(Epic epic, Users createdBy) {
        // Get all farm projects for this epic (season, variety, year combination)
        List<FarmProjects> farmProjects = farmProjectRepository.findBySeasonAndVarietyAndYear(
            epic.getSeason().getPk1(), 
            epic.getVariety().getPk1(), 
            epic.getYear().getPk1()
        );

        List<EpicRoute> generatedRoutes = new ArrayList<>();
        int maxProjectsPerRoute = epic.getMaxProjectsPerRoute() != null ? epic.getMaxProjectsPerRoute() : 8;

        // Group projects into routes of maximum size
        // Use the seed-based routing algorithm instead of the basic grouping
        generatedRoutes = generateRoutesForEpic(epic, farmProjects); // Call the new method

        return generatedRoutes;
    }

    /**
     * Generate routes for an epic using seed-based clustering algorithm
     */
    public List<EpicRoute> generateRoutesForEpic(Epic epic, List<FarmProjects> projects) {
        List<RouteLocation> locations = convertProjectsToLocations(projects);
        List<List<RouteLocation>> routeClusters = createSeedBasedRoutes(locations, epic.getMaxProjectsPerRoute());

        List<EpicRoute> epicRoutes = new ArrayList<>();

        for (int i = 0; i < routeClusters.size(); i++) {
            EpicRoute epicRoute = new EpicRoute();
            epicRoute.setEpic(epic);
            epicRoute.setRouteName("Route " + (i + 1));
            epicRoute = epicRouteRepository.save(epicRoute);

            // Assign projects to this route
            List<FarmProjects> routeProjects = new ArrayList<>();
            for (RouteLocation location : routeClusters.get(i)) {
                FarmProjects project = location.getProjects();
                project.setEpicRoute(epicRoute); // Set the EpicRoute for each project
                farmProjectRepository.save(project); // Save the updated project
                routeProjects.add(project);
            }

            epicRoute.setProjects(routeProjects);
            epicRoutes.add(epicRoute);
        }

        return epicRoutes;
    }

    /**
     * Convert FarmProjects to RouteLocation objects
     */
    private List<RouteLocation> convertProjectsToLocations(List<FarmProjects> projects) {
        List<RouteLocation> locations = new ArrayList<>();

        for (FarmProjects project : projects) {
            if (project.getLocation() != null && 
                !project.getLocation().isEmpty()) {
                try {
                    LocationDTO loc = new Gson().fromJson(project.getLocation(), LocationDTO.class);
                    if (loc.getCoords() != null && loc.getCoords().getLatitude() != null && 
                        loc.getCoords().getLongitude() != null) {
                        RouteLocation location = new RouteLocation(
                            loc.getCoords().getLatitude(),
                            loc.getCoords().getLongitude(),
                            project
                        );
                        locations.add(location);
                    }
                } catch (Exception e) {
                    // Skip projects with invalid location data
                    System.err.println("Invalid location data for project: " + project.getPk1());
                }
            }
        }

        return locations;
    }

    /**
     * Create routes using seed-based clustering algorithm
     */
    private List<List<RouteLocation>> createSeedBasedRoutes(List<RouteLocation> locations, Integer maxProjectsPerRoute) {
        List<List<RouteLocation>> routes = new ArrayList<>();
        Set<RouteLocation> usedLocations = new HashSet<>();

        // Default max projects per route if not specified
        int maxProjects = (maxProjectsPerRoute != null) ? maxProjectsPerRoute : 8;

        while (usedLocations.size() < locations.size()) {
            // Find an unused location to serve as seed
            RouteLocation seed = findSeedLocation(locations, usedLocations);
            if (seed == null) break;

            List<RouteLocation> route = createRouteFromSeed(seed, locations, usedLocations, maxProjects);
            if (!route.isEmpty()) {
                routes.add(route);
            }
        }

        return routes;
    }

    /**
     * Find the next seed location (first unused location)
     */
    private RouteLocation findSeedLocation(List<RouteLocation> locations, Set<RouteLocation> usedLocations) {
        for (RouteLocation location : locations) {
            if (!usedLocations.contains(location)) {
                return location;
            }
        }
        return null;
    }

    /**
     * Create a route starting from a seed location
     */
    private List<RouteLocation> createRouteFromSeed(RouteLocation seed, List<RouteLocation> allLocations, 
                                                    Set<RouteLocation> usedLocations, int maxProjects) {
        List<RouteLocation> route = new ArrayList<>();
        route.add(seed);
        usedLocations.add(seed);

        RouteLocation currentLocation = seed;

        // Iteratively add closest locations within distance limit
        while (route.size() < maxProjects) {
            RouteLocation closest = findClosestLocation(currentLocation, allLocations, usedLocations);

            if (closest == null) {
                break; // No more available locations
            }

            double distance = calculateDistance(currentLocation, closest);
            if (distance > MAX_DISTANCE_KM) {
                break; // Exceeded maximum distance
            }

            route.add(closest);
            usedLocations.add(closest);
            currentLocation = closest;
        }

        return route;
    }

    /**
     * Find the closest unused location to the current location
     */
    private RouteLocation findClosestLocation(RouteLocation current, List<RouteLocation> allLocations, 
                                            Set<RouteLocation> usedLocations) {
        RouteLocation closest = null;
        double minDistance = Double.MAX_VALUE;

        for (RouteLocation location : allLocations) {
            if (!usedLocations.contains(location)) {
                double distance = calculateDistance(current, location);
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = location;
                }
            }
        }

        return closest;
    }

    /**
     * Calculate distance between two locations using Haversine formula
     */
    private double calculateDistance(RouteLocation loc1, RouteLocation loc2) {
        double lat1 = Math.toRadians(loc1.getLatitude());
        double lon1 = Math.toRadians(loc1.getLongitude());
        double lat2 = Math.toRadians(loc2.getLatitude());
        double lon2 = Math.toRadians(loc2.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Earth's radius in kilometers
        double earthRadius = 6371.0;
        return earthRadius * c;
    }

    public EpicRoute createRoute(EpicRoute route, Users createdBy) {
        route.setCreatedBy(createdBy);
        return epicRouteRepository.save(route);
    }

    public EpicRoute updateRoute(Long id, EpicRoute routeDetails) {
        Optional<EpicRoute> optionalRoute = epicRouteRepository.findById(id);
        if (optionalRoute.isPresent()) {
            EpicRoute route = optionalRoute.get();
            route.setRouteName(routeDetails.getRouteName());
            return epicRouteRepository.save(route);
        }
        return null;
    }

    public void deleteRoute(Long id) {
        epicRouteRepository.deleteById(id);
    }
}