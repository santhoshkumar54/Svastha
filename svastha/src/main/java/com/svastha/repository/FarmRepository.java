package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.svastha.entity.Farms;
import com.svastha.entity.Users;

public interface FarmRepository extends JpaRepository<Farms, Long>{
	
	List<Farms> findByCreatedBy( Users user );
	
	@Query("SELECT DISTINCT f.thaluk FROM Farms f")
    List<String> findDistinctThaluks();

}
