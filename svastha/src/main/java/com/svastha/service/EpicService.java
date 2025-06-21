
package com.svastha.service;

import com.svastha.entity.Epic;
import com.svastha.entity.Users;
import com.svastha.repository.EpicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EpicService {

    @Autowired
    private EpicRepository epicRepository;

    @Autowired
    private SprintService sprintService;

    public List<Epic> getAllEpics() {
        return epicRepository.findAll();
    }

    public Optional<Epic> getEpicById(Long id) {
        return epicRepository.findById(id);
    }

    public Epic createEpic(Epic epic, Users createdBy) {
        epic.setCreatedBy(createdBy);
        return epicRepository.save(epic);
    }

    public Epic updateEpic(Long id, Epic epicDetails, Users updatedBy) {
        Optional<Epic> optionalEpic = epicRepository.findById(id);
        if (optionalEpic.isPresent()) {
            Epic epic = optionalEpic.get();
            epic.setSeason(epicDetails.getSeason());
            epic.setVariety(epicDetails.getVariety());
            epic.setYear(epicDetails.getYear());
            epic.setStartDate(epicDetails.getStartDate());
            epic.setEndDate(epicDetails.getEndDate());
            epic.setSprintSize(epicDetails.getSprintSize());
            epic.setMaxProjectsPerRoute(epicDetails.getMaxProjectsPerRoute());
            epic.setUpdatedBy(updatedBy);
            return epicRepository.save(epic);
        }
        return null;
    }

    public void deleteEpic(Long id) {
        epicRepository.deleteById(id);
    }

    public List<Epic> findBySeasonAndVarietyAndYear(Long seasonId, Long varietyId, Long yearId) {
        return epicRepository.findBySeasonPk1AndVarietyPk1AndYearPk1(seasonId, varietyId, yearId);
    }

    public List<Epic> findActiveEpics(LocalDate currentDate) {
        return epicRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(currentDate, currentDate);
    }

    public Epic createEpic(Epic epic, Users createdBy) {
        // Save the epic first
        Epic savedEpic = epicRepository.save(epic);
        
        // Automatically generate sprints for this epic
        sprintService.generateSprintsForEpic(savedEpic, createdBy);
        
        return savedEpic;
    }

    public Epic updateEpic(Long epicId, Epic epicDetails, Users updatedBy) {
        Optional<Epic> epicOpt = epicRepository.findById(epicId);
        if (epicOpt.isPresent()) {
            Epic epic = epicOpt.get();
            epic.setSeason(epicDetails.getSeason());
            epic.setVariety(epicDetails.getVariety());
            epic.setYear(epicDetails.getYear());
            epic.setStartDate(epicDetails.getStartDate());
            epic.setEndDate(epicDetails.getEndDate());
            epic.setSprintSize(epicDetails.getSprintSize());
            epic.setMaxProjectsPerRoute(epicDetails.getMaxProjectsPerRoute());
            epic.setUpdatedBy(updatedBy);
            return epicRepository.save(epic);
        }
        return null;
    }

    public void deleteEpic(Long epicId) {
        epicRepository.deleteById(epicId);
    }
}
