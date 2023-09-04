package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.Farms;
import com.svastha.entity.Users;

public interface FarmRepository extends JpaRepository<Farms, Long>{
	
	Farms findByCreatedBy( Users user );

}
