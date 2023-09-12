package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.Farms;
import com.svastha.entity.LandDetails;

public interface LandDetailsRepository extends JpaRepository<LandDetails, Long>{
	
	LandDetails findLandDetailsByFarm( Farms farm );

}
