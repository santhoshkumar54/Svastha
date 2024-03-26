package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmWaterSource;
import com.svastha.entity.Farms;

public interface FarmWaterSourceRepository extends JpaRepository<FarmWaterSource, Long> {

	List<FarmWaterSource> findAllWaterSourceByFarm( Farms farm );
	
	List<FarmWaterSource> findByFarmIn(List<Farms> farms);
}
