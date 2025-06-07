
package com.svastha.controller;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectVisit;
import com.svastha.entity.RouteAssignment;
import com.svastha.entity.Users;
import com.svastha.service.ProjectVisitService;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.service.RouteAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visits")
@CrossOrigin(origins = "*")
public class ProjectVisitController {

    @Autowired
    private ProjectVisitService projectVisitService;

    @Autowired
    private FarmProjectRepository farmProjectRepository;

    @Autowired
    private RouteAssignmentService routeAssignmentService;

    @GetMapping
    public ResponseEntity<List<ProjectVisit>> getAllVisits() {
        List<ProjectVisit> visits = projectVisitService.getAllVisits();
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectVisit> getVisitById(@PathVariable Long id) {
        Optional<ProjectVisit> visit = projectVisitService.getVisitById(id);
        return visit.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<ProjectVisit>> getVisitsByRouteAssignment(@PathVariable Long assignmentId) {
        List<ProjectVisit> visits = projectVisitService.getVisitsByRouteAssignment(assignmentId);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectVisit>> getVisitsByUser(@PathVariable Long userId) {
        List<ProjectVisit> visits = projectVisitService.getVisitsByUser(userId);
        return ResponseEntity.ok(visits);
    }

    @PostMapping("/checkin")
    public ResponseEntity<ProjectVisit> checkInToProject(
            @RequestParam Long farmProjectId,
            @RequestParam Long routeAssignmentId,
            @RequestParam Long visitedById,
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(required = false) String remarks) {

        Optional<FarmProjects> farmProject = farmProjectRepository.findById(farmProjectId);
        Optional<RouteAssignment> routeAssignment = routeAssignmentService.getAssignmentById(routeAssignmentId);

        if (farmProject.isEmpty() || routeAssignment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Users visitedBy = new Users();
        visitedBy.setPk1(visitedById);

        ProjectVisit visit = projectVisitService.checkInToProject(
                farmProject.get(), routeAssignment.get(), visitedBy, latitude, longitude, remarks);

        return ResponseEntity.status(HttpStatus.CREATED).body(visit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectVisit> updateVisit(@PathVariable Long id,
                                                   @RequestBody ProjectVisit visitDetails) {
        ProjectVisit updatedVisit = projectVisitService.updateVisit(id, visitDetails);
        return updatedVisit != null ? ResponseEntity.ok(updatedVisit)
                                    : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        projectVisitService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }
}
