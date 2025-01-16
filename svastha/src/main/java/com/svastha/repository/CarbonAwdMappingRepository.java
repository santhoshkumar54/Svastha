package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.CarbonAwdMapping;
import com.svastha.entity.FarmProjects;


public interface CarbonAwdMappingRepository extends JpaRepository<CarbonAwdMapping, Long> {
    CarbonAwdMapping findAllByProjects(FarmProjects projects);
}