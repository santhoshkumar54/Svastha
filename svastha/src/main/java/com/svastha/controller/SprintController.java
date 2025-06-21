
package com.svastha.controller;

import com.svastha.entity.Sprint;
import com.svastha.entity.Users;
import com.svastha.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @GetMapping
    public ResponseEntity<List<Sprint>> getAllSprints() {
        List<Sprint> sprints = sprintService.getAllSprints();
        return ResponseEntity.ok(sprints);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable Long id) {
        Optional<Sprint> sprint = sprintService.getSprintById(id);
        return sprint.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/epic/{epicId}")
    public ResponseEntity<List<Sprint>> getSprintsByEpic(@PathVariable Long epicId) {
        List<Sprint> sprints = sprintService.getSprintsByEpic(epicId);
        return ResponseEntity.ok(sprints);
    }

    @GetMapping("/epic/{epicId}/sprint/{sprintNumber}")
    public ResponseEntity<Sprint> getSprintByEpicAndNumber(
            @PathVariable Long epicId,
            @PathVariable Integer sprintNumber) {
        Optional<Sprint> sprint = sprintService.getSprintByEpicAndNumber(epicId, sprintNumber);
        return sprint.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/epic/{epicId}/current")
    public ResponseEntity<Sprint> getCurrentSprintForEpic(@PathVariable Long epicId) {
        Optional<Sprint> sprint = sprintService.getCurrentSprintForEpic(epicId);
        return sprint.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Sprint>> getActiveSprintsByDate(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate searchDate = date != null ? date : LocalDate.now();
        List<Sprint> sprints = sprintService.getActiveSprintsByDate(searchDate);
        return ResponseEntity.ok(sprints);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Sprint> updateSprintStatus(
            @PathVariable Long id,
            @RequestParam Sprint.SprintStatus status,
            @RequestParam Long updatedById) {
        Users updatedBy = new Users();
        updatedBy.setPk1(updatedById);
        
        Sprint updatedSprint = sprintService.updateSprintStatus(id, status, updatedBy);
        return updatedSprint != null ? ResponseEntity.ok(updatedSprint)
                                     : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Sprint> activateSprint(@PathVariable Long id,
                                               @RequestParam Long updatedById) {
        Users updatedBy = new Users();
        updatedBy.setPk1(updatedById);
        
        Sprint activatedSprint = sprintService.activateSprint(id, updatedBy);
        return activatedSprint != null ? ResponseEntity.ok(activatedSprint)
                                       : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Sprint> completeSprint(@PathVariable Long id,
                                               @RequestParam Long updatedById) {
        Users updatedBy = new Users();
        updatedBy.setPk1(updatedById);
        
        Sprint completedSprint = sprintService.completeSprint(id, updatedBy);
        return completedSprint != null ? ResponseEntity.ok(completedSprint)
                                       : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }
}
