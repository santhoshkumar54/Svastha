
package com.svastha.service;

import com.svastha.entity.AdhocTask;
import com.svastha.entity.Epic;
import com.svastha.entity.Users;
import com.svastha.entity.AdhocTask.TaskStatus;
import com.svastha.repository.AdhocTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdhocTaskService {

    @Autowired
    private AdhocTaskRepository adhocTaskRepository;

    public List<AdhocTask> getAllTasks() {
        return adhocTaskRepository.findAll();
    }

    public Optional<AdhocTask> getTaskById(Long id) {
        return adhocTaskRepository.findById(id);
    }

    public List<AdhocTask> getTasksByAssignedUser(Long userId) {
        return adhocTaskRepository.findByAssignedToPk1(userId);
    }

    public List<AdhocTask> getTasksByEpic(Long epicId) {
        return adhocTaskRepository.findByEpicPk1(epicId);
    }

    public List<AdhocTask> getTasksByDateRange(LocalDate startDate, LocalDate endDate) {
        return adhocTaskRepository.findByScheduledDateBetween(startDate, endDate);
    }

    public AdhocTask createTask(String taskName, String description, LocalDate scheduledDate, 
                               Users assignedTo, Epic epic, Users createdBy) {
        AdhocTask task = new AdhocTask();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setScheduledDate(scheduledDate);
        task.setAssignedTo(assignedTo);
        task.setEpic(epic);
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedBy(createdBy);
        
        return adhocTaskRepository.save(task);
    }

    public AdhocTask updateTask(Long id, AdhocTask taskDetails) {
        Optional<AdhocTask> optionalTask = adhocTaskRepository.findById(id);
        if (optionalTask.isPresent()) {
            AdhocTask task = optionalTask.get();
            task.setTaskName(taskDetails.getTaskName());
            task.setDescription(taskDetails.getDescription());
            task.setScheduledDate(taskDetails.getScheduledDate());
            task.setAssignedTo(taskDetails.getAssignedTo());
            task.setStatus(taskDetails.getStatus());
            return adhocTaskRepository.save(task);
        }
        return null;
    }

    public AdhocTask updateTaskStatus(Long id, TaskStatus status) {
        Optional<AdhocTask> optionalTask = adhocTaskRepository.findById(id);
        if (optionalTask.isPresent()) {
            AdhocTask task = optionalTask.get();
            task.setStatus(status);
            return adhocTaskRepository.save(task);
        }
        return null;
    }

    public void deleteTask(Long id) {
        adhocTaskRepository.deleteById(id);
    }
}
