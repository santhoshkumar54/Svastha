package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterBioFertilizer;

@Repository
public interface MasterBioFertilizerRepository extends JpaRepository<MasterBioFertilizer, Long>{

}
