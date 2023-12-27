package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterCropVariety;
import com.svastha.entity.MasterSeason;

@Repository
public interface MasterCropVarietyRepository extends JpaRepository<MasterCropVariety, Long>{

	List<MasterCropVariety> findAllBySeason(MasterSeason season);
}
