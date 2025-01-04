package com.svastha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.svastha.entity.AwdDevice;

public interface AwdDeviceRepository extends JpaRepository<AwdDevice, Long> {

	@Query("SELECT a FROM AwdDevice a ORDER BY a.pk1 DESC")
	Page<AwdDevice> findAll(Pageable pageable);

	Page<AwdDevice> findAllByPk1In(List<Long> ids, Pageable pageable);
}