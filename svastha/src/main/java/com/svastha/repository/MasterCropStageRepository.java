package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterCropStage;

@Repository
public interface MasterCropStageRepository extends JpaRepository<MasterCropStage, Long> {

	@Query("SELECT e FROM MasterCropStage e WHERE e.startDays <= :days AND e.endDays >= :days")
	List<MasterCropStage> findByCustomQuery(@Param("days") int days);

}
