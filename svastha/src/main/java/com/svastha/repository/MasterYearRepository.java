package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterYear;

@Repository
public interface MasterYearRepository extends JpaRepository<MasterYear, Long>{

}
