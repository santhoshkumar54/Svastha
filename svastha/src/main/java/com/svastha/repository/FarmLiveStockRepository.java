package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmLiveStock;
import com.svastha.entity.Farms;

public interface FarmLiveStockRepository extends JpaRepository<FarmLiveStock, Long> {

	List<FarmLiveStock> findAllLiveStocksByFarm( Farms farm );
}
