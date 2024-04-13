package com.svastha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.svastha.entity.Farms;
import com.svastha.entity.Users;

public interface FarmRepository extends JpaRepository<Farms, Long> {

	List<Farms> findByCreatedBy(Users user);

	@Query("SELECT DISTINCT f.thaluk FROM Farms f")
	List<String> findDistinctThaluks();

	@Query("SELECT f FROM Farms f WHERE (:thaluk IS NULL OR f.thalukId.pk1 = :thaluk) "
			+ "and (:district is NULL or f.districtId.pk1 = :district) "
			+ "and (:village is NULL or f.villageId.pk1 = :village) "
			+ "and (:key is NULL or f.farmerName like %:key% or f.regNumber = :key) "
			+ "and (:user is NULL or f.createdBy.pk1 = :user)" + "and (:type is NULL or f.farmerType = :type)")
	Page<Farms> findWithFilters(@Param("thaluk") Long thaluk, @Param("district") Long district,
			@Param("village") Long village, @Param("key") String key, @Param("user") Long user,
			@Param("type") String type, Pageable pageable);

	@Query("SELECT f FROM Farms f WHERE (:thaluk IS NULL OR f.thalukId.pk1 = :thaluk) "
			+ "and (:district is NULL or f.districtId.pk1 = :district) "
			+ "and (:village is NULL or f.villageId.pk1 = :village) "
			+ "and (:key is NULL or f.farmerName like %:key%) or (:key is NULL or f.regNumber = :key) "
			+ "and (:user is NULL or f.createdBy.pk1 = :user)"
			+ "and (:type is NULL or f.farmerType = :type)" )
	List<Farms> findWithFilters(@Param("thaluk") Long thaluk, @Param("district") Long district,
			@Param("village") Long village, @Param("key") String key, @Param("user") Long user,@Param("type") String type);

	@Procedure(name = "UpdateFarmCompletionPercentage")
    void updateFarmCompletionPercentage();
}
