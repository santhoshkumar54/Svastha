
package com.svastha.repository;

import com.svastha.entity.AdhocTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdhocTaskRepository extends JpaRepository<AdhocTask, Long> {

    List<AdhocTask> findByAssignedToPk1AndScheduledDate(Long userId, LocalDate date);
    
    List<AdhocTask> findByScheduledDateBetween(LocalDate startDate, LocalDate endDate);
}
