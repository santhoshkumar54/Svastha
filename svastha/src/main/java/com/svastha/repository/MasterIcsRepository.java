package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterIcs;

@Repository
public interface MasterIcsRepository extends JpaRepository<MasterIcs, Long> {

}
