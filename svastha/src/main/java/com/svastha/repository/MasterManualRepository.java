package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.Manual;

@Repository
public interface MasterManualRepository extends JpaRepository<Manual, Long> {

}
