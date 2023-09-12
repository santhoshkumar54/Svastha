package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmWorkers;
import com.svastha.entity.Farms;

public interface FarmWorkersRepository extends JpaRepository<FarmWorkers, Long> {

	FarmWorkers findAllFarmWorkersByFarm(Farms farm);

}
