package com.svastha.repository;

import com.svastha.entity.CarbonAwdMapping;
import com.svastha.entity.FarmProjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarbonAwdMappingRepository extends JpaRepository<CarbonAwdMapping, Long> {
    List<CarbonAwdMapping> findAllByProjects(FarmProjects projects);
}