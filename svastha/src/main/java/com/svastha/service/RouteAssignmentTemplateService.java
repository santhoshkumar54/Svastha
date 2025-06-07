
package com.svastha.service;

import com.svastha.entity.RouteAssignmentTemplate;
import com.svastha.entity.EpicRoute;
import com.svastha.entity.Users;
import com.svastha.repository.RouteAssignmentTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteAssignmentTemplateService {

    @Autowired
    private RouteAssignmentTemplateRepository templateRepository;

    public List<RouteAssignmentTemplate> getAllTemplates() {
        return templateRepository.findAll();
    }

    public Optional<RouteAssignmentTemplate> getTemplateById(Long id) {
        return templateRepository.findById(id);
    }

    public List<RouteAssignmentTemplate> getTemplatesByEpic(Long epicId) {
        return templateRepository.findByEpicId(epicId);
    }

    public List<RouteAssignmentTemplate> getTemplatesByUser(Long userId) {
        return templateRepository.findByAssignedToPk1(userId);
    }

    public List<RouteAssignmentTemplate> getTemplatesByRoute(Long epicRouteId) {
        return templateRepository.findByEpicRoutePk1(epicRouteId);
    }

    public List<RouteAssignmentTemplate> getTemplatesByEpicAndSprintDay(Long epicId, Integer sprintDay) {
        return templateRepository.findByEpicIdAndSprintDay(epicId, sprintDay);
    }

    public RouteAssignmentTemplate createTemplate(EpicRoute epicRoute, Users assignedTo, 
                                                 Integer sprintDay, Users createdBy) {
        // Validate sprintDay is within epic's sprint size
        if (sprintDay > epicRoute.getEpic().getSprintSize()) {
            throw new IllegalArgumentException("Sprint day cannot exceed epic's sprint size");
        }

        RouteAssignmentTemplate template = new RouteAssignmentTemplate();
        template.setEpicRoute(epicRoute);
        template.setAssignedTo(assignedTo);
        template.setSprintDay(sprintDay);
        template.setCreatedBy(createdBy);
        
        return templateRepository.save(template);
    }

    public RouteAssignmentTemplate updateTemplate(Long id, RouteAssignmentTemplate templateDetails) {
        Optional<RouteAssignmentTemplate> optionalTemplate = templateRepository.findById(id);
        if (optionalTemplate.isPresent()) {
            RouteAssignmentTemplate template = optionalTemplate.get();
            template.setAssignedTo(templateDetails.getAssignedTo());
            template.setSprintDay(templateDetails.getSprintDay());
            return templateRepository.save(template);
        }
        return null;
    }

    public void deleteTemplate(Long id) {
        templateRepository.deleteById(id);
    }
}
