package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.viewFarmerProjectByuser;

@Repository
public interface ViewFarmerProjectByuserRepository extends JpaRepository<viewFarmerProjectByuser, Long> {

}
