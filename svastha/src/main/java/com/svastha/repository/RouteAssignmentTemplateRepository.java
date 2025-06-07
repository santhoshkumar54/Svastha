
package com.svastha.repository;

import com.svastha.entity.RouteAssignmentTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteAssignmentTemplateRepository extends JpaRepository<RouteAssignmentTemplate, Long> {

    @Query("SELECT rat FROM RouteAssignmentTemplate rat WHERE rat.epicRoute.epic.pk1 = :epicId")
    List<RouteAssignmentTemplate> findByEpicId(@Param("epicId") Long epicId);

    List<RouteAssignmentTemplate> findByAssignedToPk1(Long userId);

    List<RouteAssignmentTemplate> findByEpicRoutePk1(Long epicRouteId);

    @Query("SELECT rat FROM RouteAssignmentTemplate rat WHERE rat.epicRoute.epic.pk1 = :epicId AND rat.sprintDay = :sprintDay")
    List<RouteAssignmentTemplate> findByEpicIdAndSprintDay(@Param("epicId") Long epicId, @Param("sprintDay") Integer sprintDay);
}
