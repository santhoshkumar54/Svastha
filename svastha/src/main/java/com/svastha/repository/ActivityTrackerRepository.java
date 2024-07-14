package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.ActivityTracker;

public interface ActivityTrackerRepository extends JpaRepository<ActivityTracker, Long> {

}
