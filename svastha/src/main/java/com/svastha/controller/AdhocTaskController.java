
package com.svastha.controller;

import com.svastha.entity.AdhocTask;
import com.svastha.entity.Epic;
import com.svastha.entity.Users;
import com.svastha.enums.TaskStatus;
import com.svastha.service.AdhocTaskService;
import com.svastha.service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adhoc-tasks")
@CrossOrigin(origins = "*")
public class AdhocTaskController {

    @Autowired
    private AdhocTaskService adhocTaskService;

    @Autowired
    private EpicService epicService;

    @GetMapping
    public ResponseEntity<List<AdhocTask>> getAllTasks() {
        List<AdhocTask> tasks = adhocTaskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdhocTask> getTaskById(@PathVariable Long id) {
        Optional<AdhocTask> task = adhocTaskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AdhocTask>> getTasksByUser(@PathVariable Long userId) {
        List<AdhocTask> tasks = adhocTaskService.getTasksByAssignedUser(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/epic/{epicId}")
    public ResponseEntity<List<AdhocTask>> getTasksByEpic(@PathVariable Long epicId) {
        List<AdhocTask> tasks = adhocTaskService.getTasksByEpic(epicId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/calendar")
    public ResponseEntity<List<AdhocTask>> getTasksByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<AdhocTask> tasks = adhocTaskService.getTasksByDateRange(startDate, endDate);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<AdhocTask> createTask(
            @RequestParam String taskName,
            @RequestParam(required = false) String description,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate scheduledDate,
            @RequestParam Long assignedToId,
            @RequestParam(required = false) Long epicId,
            @RequestParam Long createdById) {

        Users assignedTo = new Users();
        assignedTo.setPk1(assignedToId);

        Users createdBy = new Users();
        createdBy.setPk1(createdById);

        Epic epic = null;
        if (epicId != null) {
            Optional<Epic> optionalEpic = epicService.getEpicById(epicId);
            if (optionalEpic.isPresent()) {
                epic = optionalEpic.get();
            }
        }

        AdhocTask task = adhocTaskService.createTask(taskName, description, scheduledDate, assignedTo, epic, createdBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdhocTask> updateTask(@PathVariable Long id,
                                               @RequestBody AdhocTask taskDetails) {
        AdhocTask updatedTask = adhocTaskService.updateTask(id, taskDetails);
        return updatedTask != null ? ResponseEntity.ok(updatedTask)
                                   : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AdhocTask> updateTaskStatus(@PathVariable Long id,
                                                     @RequestParam TaskStatus status) {
        AdhocTask updatedTask = adhocTaskService.updateTaskStatus(id, status);
        return updatedTask != null ? ResponseEntity.ok(updatedTask)
                                   : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        adhocTaskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
