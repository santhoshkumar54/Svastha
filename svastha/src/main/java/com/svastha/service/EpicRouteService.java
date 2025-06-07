
package com.svastha.service;

import com.svastha.entity.Epic;
import com.svastha.entity.EpicRoute;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.Users;
import com.svastha.repository.EpicRouteRepository;
import com.svastha.repository.FarmProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EpicRouteService {

    @Autowired
    private EpicRouteRepository epicRouteRepository;

    @Autowired
    private FarmProjectRepository farmProjectRepository;

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
        for (int i = 0; i < farmProjects.size(); i += maxProjectsPerRoute) {
            EpicRoute route = new EpicRoute();
            route.setEpic(epic);
            route.setRouteName("Route-" + epic.getSeason().getSeasonName() + "-" + 
                             epic.getVariety().getVarietyName() + "-" + 
                             epic.getYear().getYear() + "-" + (i / maxProjectsPerRoute + 1));
            route.setCreatedBy(createdBy);
            
            EpicRoute savedRoute = epicRouteRepository.save(route);
            
            // Assign projects to this route
            int endIndex = Math.min(i + maxProjectsPerRoute, farmProjects.size());
            for (int j = i; j < endIndex; j++) {
                FarmProjects project = farmProjects.get(j);
                project.setEpicRoute(savedRoute);
                farmProjectRepository.save(project);
            }
            
            generatedRoutes.add(savedRoute);
        }
        
        return generatedRoutes;
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
