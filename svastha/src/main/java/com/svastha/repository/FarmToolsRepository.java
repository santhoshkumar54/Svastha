package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmTools;
import com.svastha.entity.Farms;

public interface FarmToolsRepository extends JpaRepository<FarmTools, Long> {

	List<FarmTools> findAllToolsByFarm( Farms farm );

}
