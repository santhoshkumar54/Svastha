package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.Users;

public interface FarmProjectRepository extends JpaRepository<FarmProjects, Long>{
	
	List<FarmProjects> findByCreatedBy( Users user );

}
