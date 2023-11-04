package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterGrainMarket;

@Repository
public interface MasterGrainMarketRepository extends JpaRepository<MasterGrainMarket, Long> {

}
