package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmWorkers;
import com.svastha.entity.Farms;

public interface FarmWorkersRepository extends JpaRepository<FarmWorkers, Long> {

	FarmWorkers findAllFarmWorkersByFarm(Farms farm);

	List<FarmWorkers> findByFarmIn(List<Farms> farms);
}
