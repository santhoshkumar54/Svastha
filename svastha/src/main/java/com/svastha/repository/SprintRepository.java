
package com.svastha.repository;

import com.svastha.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    List<Sprint> findByEpicPk1(Long epicId);

    List<Sprint> findByEpicPk1OrderBySprintNumber(Long epicId);

    Optional<Sprint> findByEpicPk1AndSprintNumber(Long epicId, Integer sprintNumber);

    @Query("SELECT s FROM Sprint s WHERE s.epic.pk1 = :epicId AND s.startDate <= :date AND s.endDate >= :date")
    Optional<Sprint> findActiveSprintByEpicAndDate(@Param("epicId") Long epicId, @Param("date") LocalDate date);

    @Query("SELECT s FROM Sprint s WHERE s.startDate <= :date AND s.endDate >= :date")
    List<Sprint> findActiveSprintsByDate(@Param("date") LocalDate date);

    List<Sprint> findByStatus(Sprint.SprintStatus status);
}
