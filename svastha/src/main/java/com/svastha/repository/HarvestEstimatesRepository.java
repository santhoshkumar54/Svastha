package com.svastha.repository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestEstimates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HarvestEstimatesRepository extends JpaRepository<HarvestEstimates, Long> {
    List<HarvestEstimates> findAllByProjects(FarmProjects projects);
}