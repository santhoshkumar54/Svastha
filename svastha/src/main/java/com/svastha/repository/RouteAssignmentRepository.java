
package com.svastha.repository;

import com.svastha.entity.RouteAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RouteAssignmentRepository extends JpaRepository<RouteAssignment, Long> {

    List<RouteAssignment> findByAssignedToPk1AndAssignedDate(Long userId, LocalDate date);

    @Query("SELECT ra FROM RouteAssignment ra WHERE ra.epicRoute.epic.pk1 = :epicId AND ra.sprintNumber = :sprintNumber")
    List<RouteAssignment> findByEpicAndSprint(@Param("epicId") Long epicId, @Param("sprintNumber") Integer sprintNumber);

    List<RouteAssignment> findByAssignedDateBetween(LocalDate startDate, LocalDate endDate);
}
