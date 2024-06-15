package com.svastha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.Users;

public interface FarmProjectRepository extends JpaRepository<FarmProjects, Long> {

	List<FarmProjects> findByCreatedBy(Users user);

	@Query("SELECT p FROM FarmProjects p " + "WHERE (:year IS NULL OR p.year.pk1 = :year) "
			+ "and (:season is NULL or p.season.pk1 = :season) " + "and (:crop is NULL or p.crop.pk1 = :crop) "
			+ "and (:key is NULL or p.farm.farmerName like %:key% or p.farm.regNumber = :key) "
			+ "and (:user is NULL or p.createdBy.pk1 = :user) "
			+ "and (:projectType is NULL or p.projectType.pk1 = :projectType)" + "and status = :status")
	Page<FarmProjects> findWithFilters(@Param("year") Long year, @Param("season") Long season, @Param("crop") Long crop,
			@Param("key") String key, @Param("user") Long user, @Param("projectType") Long projectType,
			@Param("status") String status, Pageable pageable);

	@Query("SELECT p FROM FarmProjects p  " + "WHERE (:year IS NULL OR p.year.pk1 = :year) "
			+ "and (:season is NULL or p.season.pk1 = :season) " + "and (:crop is NULL or p.crop.pk1 = :crop) "
			+ "and (:key is NULL or p.farm.farmerName like %:key% OR p.farm.regNumber = :key) "
			+ "and (:user is NULL or p.createdBy.pk1 = :user) "
			+ "and (:projectType is NULL or p.projectType.pk1 = :projectType)" + "and status = 'APPROVED'")
	List<FarmProjects> findWithFilters(@Param("year") Long year, @Param("season") Long season, @Param("crop") Long crop,
			@Param("key") String key, @Param("user") Long user, @Param("projectType") Long projectType);

	@Procedure(name = "UpdateFarmProjectCompletionPercentage")
	void updateFarmProjectCompletionPercentage();
}
