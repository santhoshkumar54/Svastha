package com.svastha.repository;

import com.svastha.entity.RouteMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface RouteMasterRepository extends JpaRepository<RouteMaster, Long> {


    @Query("SELECT r FROM RouteMaster r " +
            "WHERE (:routeName IS NULL OR r.routeName LIKE %:routeName%) " +
            "AND (:count IS NULL OR r.count = :count) " +
            "AND (:assignedTo IS NULL OR r.assignedTo.pk1 = :assignedTo) " +
            "AND (:assignedBy IS NULL OR r.assignedBy.pk1 = :assignedBy) " +
            "AND (:startDate IS NULL OR r.assignedDate >= :startDate) " +
            "AND (:endDate IS NULL OR r.assignedDate <= :endDate)")
    Page<RouteMaster> findWithFilters(
            @Param("routeName") String routeName,
            @Param("count") Integer count,
            @Param("assignedTo") Long assignedTo,
            @Param("assignedBy") Long assignedBy,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate,
            Pageable pageable);

}
