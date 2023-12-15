package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterCrop;
import com.svastha.entity.MasterCropVariety;

@Repository
public interface MasterCropVarietyRepository extends JpaRepository<MasterCropVariety, Long>{

	List<MasterCropVariety> findAllByCrop(MasterCrop crop);
}
