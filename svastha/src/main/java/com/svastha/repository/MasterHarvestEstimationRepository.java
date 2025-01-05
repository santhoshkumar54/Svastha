package com.svastha.repository;

import com.svastha.entity.AwdDevice;
import com.svastha.entity.MasterHarvestEstimation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterHarvestEstimationRepository extends JpaRepository<MasterHarvestEstimation, Long> {

    @Query("SELECT he FROM MasterHarvestEstimation he ORDER BY he.pk1 DESC")
    Page<MasterHarvestEstimation> findAll(Pageable pageable);
}


