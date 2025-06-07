
package com.svastha.repository;

import com.svastha.entity.ProjectVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectVisitRepository extends JpaRepository<ProjectVisit, Long> {

    List<ProjectVisit> findByRouteAssignmentPk1(Long routeAssignmentId);
    
    List<ProjectVisit> findByVisitedByPk1(Long userId);
}
