
package com.svastha.controller;

import com.svastha.entity.RouteAssignmentTemplate;
import com.svastha.entity.EpicRoute;
import com.svastha.entity.Users;
import com.svastha.service.RouteAssignmentTemplateService;
import com.svastha.service.EpicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignment-templates")
@CrossOrigin(origins = "*")
public class RouteAssignmentTemplateController {

    @Autowired
    private RouteAssignmentTemplateService templateService;

    @Autowired
    private EpicRouteService epicRouteService;

    @GetMapping
    public ResponseEntity<List<RouteAssignmentTemplate>> getAllTemplates() {
        List<RouteAssignmentTemplate> templates = templateService.getAllTemplates();
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteAssignmentTemplate> getTemplateById(@PathVariable Long id) {
        Optional<RouteAssignmentTemplate> template = templateService.getTemplateById(id);
        return template.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/epic/{epicId}")
    public ResponseEntity<List<RouteAssignmentTemplate>> getTemplatesByEpic(@PathVariable Long epicId) {
        List<RouteAssignmentTemplate> templates = templateService.getTemplatesByEpic(epicId);
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RouteAssignmentTemplate>> getTemplatesByUser(@PathVariable Long userId) {
        List<RouteAssignmentTemplate> templates = templateService.getTemplatesByUser(userId);
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<RouteAssignmentTemplate>> getTemplatesByRoute(@PathVariable Long routeId) {
        List<RouteAssignmentTemplate> templates = templateService.getTemplatesByRoute(routeId);
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/epic/{epicId}/sprint-day/{sprintDay}")
    public ResponseEntity<List<RouteAssignmentTemplate>> getTemplatesByEpicAndSprintDay(
            @PathVariable Long epicId, @PathVariable Integer sprintDay) {
        List<RouteAssignmentTemplate> templates = templateService.getTemplatesByEpicAndSprintDay(epicId, sprintDay);
        return ResponseEntity.ok(templates);
    }

    @PostMapping
    public ResponseEntity<RouteAssignmentTemplate> createTemplate(
            @RequestParam Long epicRouteId,
            @RequestParam Long assignedToId,
            @RequestParam Integer sprintDay,
            @RequestParam Long createdById) {

        Optional<EpicRoute> epicRoute = epicRouteService.getRouteById(epicRouteId);
        if (epicRoute.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Users assignedTo = new Users();
        assignedTo.setPk1(assignedToId);

        Users createdBy = new Users();
        createdBy.setPk1(createdById);

        try {
            RouteAssignmentTemplate template = templateService.createTemplate(
                    epicRoute.get(), assignedTo, sprintDay, createdBy);
            return ResponseEntity.status(HttpStatus.CREATED).body(template);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteAssignmentTemplate> updateTemplate(@PathVariable Long id,
                                                                 @RequestBody RouteAssignmentTemplate templateDetails) {
        RouteAssignmentTemplate updatedTemplate = templateService.updateTemplate(id, templateDetails);
        return updatedTemplate != null ? ResponseEntity.ok(updatedTemplate)
                                       : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        templateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}
