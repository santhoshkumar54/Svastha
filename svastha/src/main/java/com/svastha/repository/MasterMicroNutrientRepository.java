package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterMicroNutrient;

@Repository
public interface MasterMicroNutrientRepository extends JpaRepository<MasterMicroNutrient, Long>{

}
