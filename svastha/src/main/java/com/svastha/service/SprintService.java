
package com.svastha.service;

import com.svastha.entity.Epic;
import com.svastha.entity.Sprint;
import com.svastha.entity.Users;
import com.svastha.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    public Optional<Sprint> getSprintById(Long id) {
        return sprintRepository.findById(id);
    }

    public List<Sprint> getSprintsByEpic(Long epicId) {
        return sprintRepository.findByEpicPk1OrderBySprintNumber(epicId);
    }

    public Optional<Sprint> getSprintByEpicAndNumber(Long epicId, Integer sprintNumber) {
        return sprintRepository.findByEpicPk1AndSprintNumber(epicId, sprintNumber);
    }

    public Optional<Sprint> getActiveSprintByEpic(Long epicId, LocalDate date) {
        return sprintRepository.findActiveSprintByEpicAndDate(epicId, date);
    }

    public List<Sprint> getActiveSprintsByDate(LocalDate date) {
        return sprintRepository.findActiveSprintsByDate(date);
    }

    /**
     * Automatically generates all sprints for an epic based on its start date, end date, and sprint size
     */
    public List<Sprint> generateSprintsForEpic(Epic epic, Users createdBy) {
        List<Sprint> sprints = new ArrayList<>();
        
        LocalDate currentSprintStart = epic.getStartDate();
        LocalDate epicEndDate = epic.getEndDate();
        Integer sprintSize = epic.getSprintSize();
        int sprintNumber = 1;

        while (currentSprintStart.isBefore(epicEndDate) || currentSprintStart.isEqual(epicEndDate)) {
            LocalDate currentSprintEnd = currentSprintStart.plusDays(sprintSize - 1);
            
            // If the sprint end date goes beyond epic end date, adjust it
            if (currentSprintEnd.isAfter(epicEndDate)) {
                currentSprintEnd = epicEndDate;
            }

            Sprint sprint = new Sprint();
            sprint.setEpic(epic);
            sprint.setSprintNumber(sprintNumber);
            sprint.setStartDate(currentSprintStart);
            sprint.setEndDate(currentSprintEnd);
            sprint.setStatus(Sprint.SprintStatus.PLANNED);
            sprint.setCreatedBy(createdBy);

            sprints.add(sprintRepository.save(sprint));

            // Move to next sprint start date
            currentSprintStart = currentSprintEnd.plusDays(1);
            sprintNumber++;
        }

        return sprints;
    }

    public Sprint updateSprintStatus(Long sprintId, Sprint.SprintStatus status, Users updatedBy) {
        Optional<Sprint> sprintOpt = sprintRepository.findById(sprintId);
        if (sprintOpt.isPresent()) {
            Sprint sprint = sprintOpt.get();
            sprint.setStatus(status);
            sprint.setUpdatedBy(updatedBy);
            return sprintRepository.save(sprint);
        }
        return null;
    }

    public void deleteSprint(Long sprintId) {
        sprintRepository.deleteById(sprintId);
    }

    /**
     * Get current active sprint for an epic
     */
    public Optional<Sprint> getCurrentSprintForEpic(Long epicId) {
        return getActiveSprintByEpic(epicId, LocalDate.now());
    }

    /**
     * Activate a sprint (set status to ACTIVE)
     */
    public Sprint activateSprint(Long sprintId, Users updatedBy) {
        return updateSprintStatus(sprintId, Sprint.SprintStatus.ACTIVE, updatedBy);
    }

    /**
     * Complete a sprint (set status to COMPLETED)
     */
    public Sprint completeSprint(Long sprintId, Users updatedBy) {
        return updateSprintStatus(sprintId, Sprint.SprintStatus.COMPLETED, updatedBy);
    }
}
