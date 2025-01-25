package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.QueueJob;

@Repository
public interface QueueJobRepository extends JpaRepository<QueueJob, Long> {
	List<QueueJob> findAllByStatus(String status);

	List<QueueJob> findAllByJobType(String jobType);
}