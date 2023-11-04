package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterSoiltype;

@Repository
public interface MasterSoilTypeRepository extends JpaRepository<MasterSoiltype, Long> {

}
