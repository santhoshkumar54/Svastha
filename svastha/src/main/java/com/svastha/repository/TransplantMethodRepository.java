package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.TransplantMethods;

@Repository
public interface TransplantMethodRepository extends JpaRepository<TransplantMethods, Long>{

}
