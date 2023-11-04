package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterTools;

@Repository
public interface MasterToolsRepository extends JpaRepository<MasterTools, Long> {

}
