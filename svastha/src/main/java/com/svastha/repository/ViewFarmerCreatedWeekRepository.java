package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.viewFarmerCreatedweek;

@Repository
public interface ViewFarmerCreatedWeekRepository extends JpaRepository<viewFarmerCreatedweek, Long> {

}
