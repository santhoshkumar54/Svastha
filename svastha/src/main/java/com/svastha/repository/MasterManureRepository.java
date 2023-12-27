package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterOrganicManure;

@Repository
public interface MasterManureRepository extends JpaRepository<MasterOrganicManure, Long>{

}
