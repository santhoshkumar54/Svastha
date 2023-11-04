package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.Farms;

public interface FarmPlotsRepository extends JpaRepository<FarmPlots, Long> {

	List<FarmPlots> findAllPlotsByFarm(Farms farm);

}
