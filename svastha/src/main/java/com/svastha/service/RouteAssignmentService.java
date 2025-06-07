package com.svastha.service;

import com.svastha.entity.RouteAssignment;
import com.svastha.entity.RouteAssignmentTemplate;
import com.svastha.entity.EpicRoute;
import com.svastha.entity.Users;
import com.svastha.repository.RouteAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.svastha.entity.Epic;
import com.svastha.enums.AssignmentStatus;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RouteAssignmentService {

    @Autowired
    private RouteAssignmentRepository routeAssignmentRepository;

    public List<RouteAssignment> getAllAssignments() {
        return routeAssignmentRepository.findAll();
    }

    public Optional<RouteAssignment> getAssignmentById(Long id) {
        return routeAssignmentRepository.findById(id);
    }

    public List<RouteAssignment> getAssignmentsByUserAndDate(Long userId, LocalDate date) {
        return routeAssignmentRepository.findByAssignedToPk1AndAssignedDate(userId, date);
    }

    public List<RouteAssignment> getAssignmentsByEpicAndSprint(Long epicId, Integer sprintNumber) {
        return routeAssignmentRepository.findByEpicAndSprint(epicId, sprintNumber);
    }

    public List<RouteAssignment> getAssignmentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return routeAssignmentRepository.findByAssignedDateBetween(startDate, endDate);
    }

    public RouteAssignment createAssignment(EpicRoute epicRoute, Users assignedTo, 
                                          LocalDate assignedDate, Integer sprintNumber, Users assignedBy) {
        RouteAssignment assignment = new RouteAssignment();
        assignment.setEpicRoute(epicRoute);
        assignment.setAssignedTo(assignedTo);
        assignment.setAssignedDate(assignedDate);
        assignment.setSprintNumber(sprintNumber);
        assignment.setStatus(AssignmentStatus.ASSIGNED);
        assignment.setAssignedBy(assignedBy);

        return routeAssignmentRepository.save(assignment);
    }

    public RouteAssignment updateAssignment(Long id, RouteAssignment assignmentDetails) {
        Optional<RouteAssignment> optionalAssignment = routeAssignmentRepository.findById(id);
        if (optionalAssignment.isPresent()) {
            RouteAssignment assignment = optionalAssignment.get();
            assignment.setAssignedDate(assignmentDetails.getAssignedDate());
            assignment.setAssignedTo(assignmentDetails.getAssignedTo());
            assignment.setStatus(assignmentDetails.getStatus());
            return routeAssignmentRepository.save(assignment);
        }
        return null;
    }

    public RouteAssignment moveAssignmentToDate(Long assignmentId, LocalDate newDate) {
        Optional<RouteAssignment> optionalAssignment = routeAssignmentRepository.findById(assignmentId);
        if (optionalAssignment.isPresent()) {
            RouteAssignment assignment = optionalAssignment.get();
            assignment.setAssignedDate(newDate);
            return routeAssignmentRepository.save(assignment);
        }
        return null;
    }

    public void deleteAssignment(Long id) {
        routeAssignmentRepository.deleteById(id);
    }

    @Autowired
    private RouteAssignmentTemplateService templateService;

    public List<RouteAssignment> generateSprintAssignments(Long epicId, Integer sprintNumber, LocalDate sprintStartDate) {
        List<RouteAssignmentTemplate> templates = templateService.getTemplatesByEpic(epicId);
        List<RouteAssignment> assignments = new ArrayList<>();

        for (RouteAssignmentTemplate template : templates) {
            LocalDate assignedDate = sprintStartDate.plusDays(template.getSprintDay() - 1);

            RouteAssignment assignment = new RouteAssignment();
            assignment.setRouteAssignmentTemplate(template);
            assignment.setAssignedDate(assignedDate);
            assignment.setSprintNumber(sprintNumber);
            assignment.setSprintDay(template.getSprintDay());
            assignment.setStatus(AssignmentStatus.ASSIGNED);
            assignment.setAssignedBy(template.getCreatedBy());

            assignments.add(routeAssignmentRepository.save(assignment));
        }

        return assignments;
    }
}