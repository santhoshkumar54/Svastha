
package com.svastha.service;

import com.google.gson.Gson;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectVisit;
import com.svastha.entity.RouteAssignment;
import com.svastha.entity.Users;
import com.svastha.enums.VisitStatus;
import com.svastha.repository.ProjectVisitRepository;
import com.svastha.util.LocationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectVisitService {

    @Autowired
    private ProjectVisitRepository projectVisitRepository;

    public List<ProjectVisit> getAllVisits() {
        return projectVisitRepository.findAll();
    }

    public Optional<ProjectVisit> getVisitById(Long id) {
        return projectVisitRepository.findById(id);
    }

    public List<ProjectVisit> getVisitsByRouteAssignment(Long routeAssignmentId) {
        return projectVisitRepository.findByRouteAssignmentPk1(routeAssignmentId);
    }

    public List<ProjectVisit> getVisitsByUser(Long userId) {
        return projectVisitRepository.findByVisitedByPk1(userId);
    }

    public ProjectVisit checkInToProject(FarmProjects farmProject, RouteAssignment routeAssignment, 
                                       Users visitedBy, Double latitude, Double longitude, String remarks) {
        // Validate location proximity (implementation needed)
        boolean isLocationValid = validateLocation(farmProject, latitude, longitude);
        
        ProjectVisit visit = new ProjectVisit();
        visit.setFarmProject(farmProject);
        visit.setRouteAssignment(routeAssignment);
        visit.setVisitedBy(visitedBy);
        visit.setCheckInTime(new Timestamp(System.currentTimeMillis()));
        visit.setLatitude(latitude);
        visit.setLongitude(longitude);
        visit.setRemarks(remarks);
        visit.setStatus(isLocationValid ? VisitStatus.COMPLETED : VisitStatus.LOCATION_MISMATCH);
        
        return projectVisitRepository.save(visit);
    }

    public ProjectVisit updateVisit(Long id, ProjectVisit visitDetails) {
        Optional<ProjectVisit> optionalVisit = projectVisitRepository.findById(id);
        if (optionalVisit.isPresent()) {
            ProjectVisit visit = optionalVisit.get();
            visit.setRemarks(visitDetails.getRemarks());
            visit.setStatus(visitDetails.getStatus());
            return projectVisitRepository.save(visit);
        }
        return null;
    }

    private boolean validateLocation(FarmProjects farmProject, Double userLatitude, Double userLongitude) {
        // Implementation for location validation
        // Compare user's location with farm project's location
        // Allow some tolerance (e.g., within 100 meters)
        if (farmProject.getLocation() == null ) {
            return true; // Skip validation if farm coordinates not available
        }
        LocationDTO loc = new Gson().fromJson(farmProject.getLocation(), LocationDTO.class);

        double distance = calculateDistance(
            loc.getCoords().getLatitude(), loc.getCoords().getLongitude(),
            userLatitude, userLongitude
        );
        
        return distance <= 0.1; // 100 meters tolerance
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Haversine formula for calculating distance between two points
        final int R = 6371; // Radius of the earth in km
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        
        return distance;
    }

    public void deleteVisit(Long id) {
        projectVisitRepository.deleteById(id);
    }
}
