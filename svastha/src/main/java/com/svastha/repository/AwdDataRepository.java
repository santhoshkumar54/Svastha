package com.svastha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.AwdData;

public interface AwdDataRepository extends JpaRepository<AwdData, Long> {
	
	Page<AwdData> findAllByDeviceId(String id, Pageable pageable);

}
