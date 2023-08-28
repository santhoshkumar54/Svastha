package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.CropType;

@Repository
public interface CropTypeRepository extends JpaRepository<CropType, Long>{

	List<CropType> findAllByCrop(int crop);
}
