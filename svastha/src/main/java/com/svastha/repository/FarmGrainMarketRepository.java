package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmGrainMarket;
import com.svastha.entity.Farms;

public interface FarmGrainMarketRepository extends JpaRepository<FarmGrainMarket, Long> {

	List<FarmGrainMarket> findAllGrainMarketByFarm( Farms farm );

}
