package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterWeedicide;

@Repository
public interface MasterWeedicideRepository extends JpaRepository<MasterWeedicide, Long> {

}
