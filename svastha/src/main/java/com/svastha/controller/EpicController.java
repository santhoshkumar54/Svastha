package com.svastha.controller;

import com.svastha.entity.Epic;
import com.svastha.entity.EpicRoute;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.Users;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.service.EpicRouteService;
import com.svastha.service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/epics")
@CrossOrigin(origins = "*")
public class EpicController {

    @Autowired
    private EpicService epicService;

    @Autowired
    private EpicRouteService epicRouteService;

    @Autowired
    private FarmProjectRepository farmProjectRepository;

    @GetMapping
    public ResponseEntity<List<Epic>> getAllEpics() {
        List<Epic> epics = epicService.getAllEpics();
        return ResponseEntity.ok(epics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Epic> getEpicById(@PathVariable Long id) {
        Optional<Epic> epic = epicService.getEpicById(id);
        return epic.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Epic> createEpic(@RequestBody Epic epic, @RequestParam Long createdById) {
        Users createdBy = new Users();
        createdBy.setPk1(createdById);
        epic.setCreatedBy(createdBy);

        Epic savedEpic = epicService.createEpic(epic, createdBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEpic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Epic> updateEpic(@PathVariable Long id,
                                          @RequestBody Epic epicDetails,
                                          @RequestParam Long updatedById) {
        Users updatedBy = new Users();
        updatedBy.setPk1(updatedById);

        Epic updatedEpic = epicService.updateEpic(id, epicDetails, updatedBy);
        return updatedEpic != null ? ResponseEntity.ok(updatedEpic)
                                   : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpic(@PathVariable Long id) {
        epicService.deleteEpic(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Epic>> searchEpics(@RequestParam Long seasonId,
                                                 @RequestParam Long varietyId,
                                                 @RequestParam Long yearId) {
        List<Epic> epics = epicService.findBySeasonAndVarietyAndYear(seasonId, varietyId, yearId);
        return ResponseEntity.ok(epics);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Epic>> getActiveEpics() {
        List<Epic> activeEpics = epicService.findActiveEpics(LocalDate.now());
        return ResponseEntity.ok(activeEpics);
    }

    @PostMapping("/{epicId}/generate-routes")
    public ResponseEntity<List<EpicRoute>> generateRoutesForEpic(@PathVariable Long epicId) {
        try {
            Optional<Epic> epicOpt = epicService.getEpicById(epicId);
            if (!epicOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Epic epic = epicOpt.get();

            // Get all approved projects with valid locations for this epic
            List<FarmProjects> projects = farmProjectRepository.findWithLocations();

            // Generate routes using seed-based clustering
            List<EpicRoute> generatedRoutes = epicRouteService.generateRoutesForEpic(epic, projects);

            return ResponseEntity.ok(generatedRoutes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}