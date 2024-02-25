package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.Village;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {

}
