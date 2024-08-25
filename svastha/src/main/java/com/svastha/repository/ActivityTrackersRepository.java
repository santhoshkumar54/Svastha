package com.svastha.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.svastha.entity.ActivityTrackers;

public interface ActivityTrackersRepository extends JpaRepository<ActivityTrackers, Long> {

	@Query("SELECT p FROM ActivityTrackers p " + "WHERE (:section IS NULL OR p.activitySection = :section) "
			+ "and (:user is NULL or p.user.pk1 = :user) "
			+ "and (:startDate is NULL or p.activityDate >= :startDate) "
			+ "and (:endDate is NULL or p.activityDate <=  :endDate) "
			+ " ORDER BY p.activityDate DESC")
	Page<ActivityTrackers> findWithFilters(@Param("section") String section, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("user") Long user, Pageable pageable);

	@Query("SELECT p FROM ActivityTrackers p " + "WHERE (:section IS NULL OR p.activitySection = :section) "
			+ "and (:id is NULL or p.primaryKey = :id)")
	Page<ActivityTrackers> findWithId(@Param("id") Long id, @Param("section") String section, Pageable pageable);

	@Procedure(name = "populateactivitytracker")
	void upadteActivityTrackers(int days);
}
