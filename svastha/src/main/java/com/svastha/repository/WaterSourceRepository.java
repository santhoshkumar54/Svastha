package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.Farms;
import com.svastha.entity.WaterSource;

@Repository
public interface WaterSourceRepository extends JpaRepository<WaterSource, Long>{

}
