
package com.svastha.controller;

import com.svastha.entity.EpicRoute;
import com.svastha.entity.RouteAssignment;
import com.svastha.entity.Users;
import com.svastha.service.RouteAssignmentService;
import com.svastha.service.EpicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class RouteAssignmentController {

    @Autowired
    private RouteAssignmentService routeAssignmentService;

    @Autowired
    private EpicRouteService epicRouteService;

    @GetMapping
    public ResponseEntity<List<RouteAssignment>> getAllAssignments() {
        List<RouteAssignment> assignments = routeAssignmentService.getAllAssignments();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteAssignment> getAssignmentById(@PathVariable Long id) {
        Optional<RouteAssignment> assignment = routeAssignmentService.getAssignmentById(id);
        return assignment.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}/date/{date}")
    public ResponseEntity<List<RouteAssignment>> getAssignmentsByUserAndDate(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<RouteAssignment> assignments = routeAssignmentService.getAssignmentsByUserAndDate(userId, date);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/epic/{epicId}/sprint/{sprintNumber}")
    public ResponseEntity<List<RouteAssignment>> getAssignmentsByEpicAndSprint(
            @PathVariable Long epicId,
            @PathVariable Integer sprintNumber) {
        List<RouteAssignment> assignments = routeAssignmentService.getAssignmentsByEpicAndSprint(epicId, sprintNumber);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/calendar")
    public ResponseEntity<List<RouteAssignment>> getAssignmentsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<RouteAssignment> assignments = routeAssignmentService.getAssignmentsByDateRange(startDate, endDate);
        return ResponseEntity.ok(assignments);
    }

    @PostMapping
    public ResponseEntity<RouteAssignment> createAssignment(
            @RequestParam Long epicRouteId,
            @RequestParam Long assignedToId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate assignedDate,
            @RequestParam Integer sprintNumber,
            @RequestParam Long assignedById) {

        Optional<EpicRoute> epicRoute = epicRouteService.getRouteById(epicRouteId);
        if (epicRoute.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Users assignedTo = new Users();
        assignedTo.setPk1(assignedToId);

        Users assignedBy = new Users();
        assignedBy.setPk1(assignedById);

        RouteAssignment assignment = routeAssignmentService.createAssignment(
                epicRoute.get(), assignedTo, assignedDate, sprintNumber, assignedBy);

        return ResponseEntity.status(HttpStatus.CREATED).body(assignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteAssignment> updateAssignment(@PathVariable Long id,
                                                           @RequestBody RouteAssignment assignmentDetails) {
        RouteAssignment updatedAssignment = routeAssignmentService.updateAssignment(id, assignmentDetails);
        return updatedAssignment != null ? ResponseEntity.ok(updatedAssignment)
                                         : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<RouteAssignment> moveAssignmentToDate(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newDate) {
        RouteAssignment movedAssignment = routeAssignmentService.moveAssignmentToDate(id, newDate);
        return movedAssignment != null ? ResponseEntity.ok(movedAssignment)
                                       : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        routeAssignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
