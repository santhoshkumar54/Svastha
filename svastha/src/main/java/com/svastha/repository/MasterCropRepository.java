package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterCrop;

@Repository
public interface MasterCropRepository extends JpaRepository<MasterCrop, Long>{

}
