package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CropType;

@Repository
public interface CropTypeRepository extends JpaRepository<CropType, Long>{

	List<CropType> findAllByCrop(int crop);
}
