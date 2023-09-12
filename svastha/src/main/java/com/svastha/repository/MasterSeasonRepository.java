package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterSeason;

@Repository
public interface MasterSeasonRepository extends JpaRepository<MasterSeason, Long> {

}
