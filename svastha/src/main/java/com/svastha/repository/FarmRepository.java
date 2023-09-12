package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.Farms;
import com.svastha.entity.Users;

public interface FarmRepository extends JpaRepository<Farms, Long>{
	
	List<Farms> findByCreatedBy( Users user );

}
