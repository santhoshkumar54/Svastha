package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.ViewFarmerThaluk;

@Repository
public interface ViewFarmerThalukRepository extends JpaRepository<ViewFarmerThaluk, Long> {

}
