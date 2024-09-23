package com.svastha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.Farms;
import com.svastha.entity.Users;
import com.svastha.model.ProjectExportModel;

public interface FarmProjectRepository extends JpaRepository<FarmProjects, Long> {

	List<FarmProjects> findByCreatedBy(Users user);

	@Query("SELECT p FROM FarmProjects p " + "WHERE (:year IS NULL OR p.year.pk1 = :year) "
			+ "and (:season is NULL or p.season.pk1 = :season) " + "and (:crop is NULL or p.crop.pk1 = :crop) "
			+ "and (:districtId is NULL or p.farm.districtId.pk1 = :districtId) " + "and (:thalukId is NULL or p.farm.thalukId.pk1 = :thalukId) "
			+ "and (:villageId is NULL or p.farm.villageId.pk1 = :villageId) " + "and (:key is NULL or p.farm.farmerName like %:key% or p.farm.regNumber = :key) "
			+ "and (:user is NULL or p.assignedTo.pk1 = :user) "
			+ "and (:varietyId is NULL or p.variety.pk1 = :varietyId) " + "and (:ics is NULL or p.ics.pk1 = :ics) "
			+ "and (:projectType is NULL or p.projectType.pk1 = :projectType)" + "and status = :status"
			+ " ORDER BY p.pk1 DESC")
	Page<FarmProjects> findWithFilters(@Param("year") Long year, @Param("season") Long season, @Param("crop") Long crop,
			@Param("key") String key, @Param("user") Long user, @Param("projectType") Long projectType,
			@Param("varietyId") Long varietyId, @Param("ics") Long ics, @Param("status") String status,
			@Param("districtId") Long districtId, @Param("thalukId") Long thalukId,
			@Param("villageId") Long villageId, Pageable pageable);

	@Query("SELECT p FROM FarmProjects p  " + "WHERE (:year IS NULL OR p.year.pk1 = :year) "
			+ "and (:season is NULL or p.season.pk1 = :season) " + "and (:crop is NULL or p.crop.pk1 = :crop) "
			+ "and (:districtId is NULL or p.farm.districtId.pk1 = :districtId) " + "and (:thalukId is NULL or p.farm.thalukId.pk1 = :thalukId) "
			+ "and (:villageId is NULL or p.farm.villageId.pk1 = :villageId) " + "and (:key is NULL or p.farm.farmerName like %:key% OR p.farm.regNumber = :key) "
			+ "and (:user is NULL or p.assignedTo.pk1 = :user) "
			+ "and (:varietyId is NULL or p.variety.pk1 = :varietyId) " + "and (:ics is NULL or p.ics.pk1 = :ics) "
			+ "and (:projectType is NULL or p.projectType.pk1 = :projectType)" + "and status = 'APPROVED'"
			+ " ORDER BY p.pk1 DESC")
	List<FarmProjects> findWithFilters(@Param("year") Long year, @Param("season") Long season, @Param("crop") Long crop,
			@Param("key") String key, @Param("user") Long user, @Param("projectType") Long projectType,
			@Param("varietyId") Long varietyId, @Param("ics") Long ics, @Param("districtId") Long districtId, @Param("thalukId") Long thalukId,
			@Param("villageId") Long villageId);

	@Query("SELECT p FROM FarmProjects p join p.farm f " + "WHERE p.status = 'APPROVED' and f.location != '' "
			+ " ORDER BY p.pk1 DESC")
	List<FarmProjects> findWithLocations();

	@Procedure(name = "UpdateFarmProjectCompletionPercentage")
	void updateFarmProjectCompletionPercentage();
	
	@Procedure(name = "UpdateOrganicProjectCompletionPercentage")
	void updateOrganicProjectCompletionPercentage();

	List<FarmProjects> findAllByFarm(Farms farm);
}
