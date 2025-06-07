
package com.svastha.controller;

import com.svastha.entity.Epic;
import com.svastha.entity.EpicRoute;
import com.svastha.entity.Users;
import com.svastha.service.EpicRouteService;
import com.svastha.service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class EpicRouteController {

    @Autowired
    private EpicRouteService epicRouteService;

    @Autowired
    private EpicService epicService;

    @GetMapping
    public ResponseEntity<List<EpicRoute>> getAllRoutes() {
        List<EpicRoute> routes = epicRouteService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpicRoute> getRouteById(@PathVariable Long id) {
        Optional<EpicRoute> route = epicRouteService.getRouteById(id);
        return route.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/epic/{epicId}")
    public ResponseEntity<List<EpicRoute>> getRoutesByEpic(@PathVariable Long epicId) {
        List<EpicRoute> routes = epicRouteService.getRoutesByEpic(epicId);
        return ResponseEntity.ok(routes);
    }

    @PostMapping("/generate/{epicId}")
    public ResponseEntity<List<EpicRoute>> generateRoutesForEpic(@PathVariable Long epicId,
                                                               @RequestParam Long createdById) {
        Optional<Epic> epic = epicService.getEpicById(epicId);
        if (epic.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Users createdBy = new Users();
        createdBy.setPk1(createdById);

        List<EpicRoute> generatedRoutes = epicRouteService.generateRoutesForEpic(epic.get(), createdBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedRoutes);
    }

    @PostMapping
    public ResponseEntity<EpicRoute> createRoute(@RequestBody EpicRoute route,
                                                @RequestParam Long createdById) {
        Users createdBy = new Users();
        createdBy.setPk1(createdById);

        EpicRoute createdRoute = epicRouteService.createRoute(route, createdBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EpicRoute> updateRoute(@PathVariable Long id,
                                               @RequestBody EpicRoute routeDetails) {
        EpicRoute updatedRoute = epicRouteService.updateRoute(id, routeDetails);
        return updatedRoute != null ? ResponseEntity.ok(updatedRoute)
                                    : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        epicRouteService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
